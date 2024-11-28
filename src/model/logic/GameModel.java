package model.logic;


import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import com.sun.source.tree.NewArrayTree;
import model.commands.Command;
import model.enums.EVENT;
import model.exceptions.*;
import model.factorys.*;
import model.interfaces.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GameModel extends ObservableRemoto implements IGameModel, Serializable {

    private static IGameModel instance = null;
    private final ILog log;
    private final IRanking ranking;
    private final IGameMatchFactory gameMatchFactory;
    private Map<Integer, IGameMatch> matches;
    private IGameMatch gameMatch;
    private final IPlayerManager playerManager;
    private final IRoundManager roundManager;
    private final IGamePersistence gamePersistence;
    private boolean exists = false;

    private GameModel(IGameMatchFactory gameMatchFactory) {
        log = Log.getInstance();
        ranking = Ranking.getInstance();
        playerManager = PlayerManager.getInstance(new PlayerFactory());
        roundManager = RoundManager.getInstance();
        gamePersistence = GamePersistence.getInstance();
        this.gameMatchFactory = gameMatchFactory;
    }

    public static IGameModel getInstance(IGameMatchFactory gameMatchFactory) throws RemoteException {
        if (instance == null) instance = new GameModel(gameMatchFactory);
        return instance;
    }

    private ILog getLog() throws RemoteException {
        return log;
    }

    private IRanking getRanking() throws RemoteException {
        return ranking;
    }

    private IGameMatch getGameMatch() throws RemoteException {
        return gameMatch;
    }

    @Override
    public int signIn(String userName) throws RemoteException, NonExistsPlayerException {
        return getLog().signIn(userName);
    }

    @Override
    public void signUp(String userName) throws RemoteException, PlayerAlreadyExistsException {
        getLog().signUp(userName);
    }

//    @Override
//    public int initGame(int limitPoints, int numOfPlayers) throws RemoteException, InvalidLimitPointsException, InvalidNumOfPlayerException {
//        gameMatch = gameMatchFactory.createGameMatch(
//                new CenterFactory(),
//                new TurnFactory(),
//                new RoundFactory(),
//                new PlayerGroup(),
//                new Deck(new CardFactory()),
//                limitPoints,
//                numOfPlayers
//        );
//        exists = true;
//        return gameMatch.getId();
//    }

    @Override
    public int initGame(int limitPoints, int numOfPlayers) throws RemoteException, InvalidLimitPointsException, InvalidNumOfPlayerException {
        IGameMatch gameMatchT = gameMatchFactory.createGameMatch(
                new CenterFactory(),
                new TurnFactory(),
                new RoundFactory(),
                new PlayerGroup(),
                new Deck(new CardFactory()),
                limitPoints,
                numOfPlayers
        );
        matches.put(gameMatchT.getId(),gameMatchT);
        exists = true;
        return gameMatch.getId();
    }

    @Override
    public void close(IObservadorRemoto obs, int playerID) throws RemoteException {
        this.removerObservador(obs);
        playerManager.disconnectPlayer(gameMatch,playerID);
        notificarObservadores(EVENT.DISCONNECT_PLAYER);
    }

    @Override
    public void playTurn(int indexCard, int indexCenter) throws RemoteException, CardIndexOutOfBoundsException, InvalidTypeCardException, LostCardException {
        GameInvoker invoker = new GameInvoker();

        invoker.addCommand(() -> checkValidMoveHandler(roundManager,gameMatch,indexCard,indexCenter));
        invoker.addCommand(() -> playTurnHandler(roundManager,gameMatch,indexCard,indexCenter));
        invoker.addCommand(() -> nextTurnHandler(roundManager,gameMatch));
        invoker.addCommand(() -> nextRoundHandler(roundManager,gameMatch));
        invoker.addCommand(() -> resetRoundHandler(roundManager,gameMatch));
        invoker.addCommand(() -> hasWinnerHandler(roundManager,gameMatch));

        invoker.executeCommands();
    }

    @Override
    public void connectPLayer(String userName, int id) throws RemoteException, LostCardException {
        playerManager.connectPlayer(getGameMatch(),userName, id);
        notificarObservadores(EVENT.CONNECT_PLAYER);
        if(playerManager.isAllPlayersConnect(getGameMatch())){
            getGameMatch().startGame();
            notificarObservadores(EVENT.ALL_PLAYERS_CONNECT);
        }
    }

    @Override
    public boolean isPlayerConnect(int id) throws RemoteException {
        return gameMatch.getPlayerGroup().isPLayerConnect(playerManager.getPlayerByID(gameMatch,id));
    }

    @Override
    public IPlayer getCurrentPlayer() throws RemoteException {
        return gameMatch.getTurn().getCurrentPlayer();
    }

    @Override
    public void loadGame() throws RemoteException {
        gamePersistence.loadGame(gameMatch,playerManager);
        notificarObservadores(EVENT.LOAD_GAME);
    }

    @Override
    public void saveGame() throws RemoteException {
        gamePersistence.saveGame(gameMatch,playerManager);
        notificarObservadores(EVENT.SAVE_GAME);
    }

    @Override
    public List<ICenterStack> getAllCenters() throws RemoteException {
        return getGameMatch().getAllCenters();
    }

    @Override
    public List<IPlayer> getAllPlayers() throws RemoteException {
        return getGameMatch().getTurn().getPlayersAlive();
    }

    @Override
    public IPlayer getPlayerByID(int id) throws RemoteException {
        return playerManager.getPlayerByID(getGameMatch(),id);
    }

    @Override
    public boolean isExists() throws RemoteException{
        return exists;
    }

    private class GameInvoker implements Serializable {
        private final List<Command> commands = new ArrayList<>();

        public void addCommand(Command command){
            commands.add(command);
        }

        public void executeCommands() throws RemoteException, CardIndexOutOfBoundsException, InvalidTypeCardException, LostCardException {
            for (Command c : commands){
                c.execute();
            }
        }
    }

    private void checkValidMoveHandler(IRoundManager roundManager, IGameMatch gameMatch, int indexCard, int indexCenter) throws CardIndexOutOfBoundsException, InvalidTypeCardException {
        boolean valid = roundManager.checkValidMove(gameMatch,indexCard,indexCenter);
        if(!valid){
            throw new InvalidTypeCardException("Invalid Move");
        }
    }

    private void playTurnHandler(IRoundManager roundManager, IGameMatch gameMatch, int indexCard, int indexCenter) throws CardIndexOutOfBoundsException, RemoteException {
        roundManager.playTurn(gameMatch,indexCard,indexCenter);
        notificarObservadores(EVENT.PLAYER_PLAYED_CARD);
    }

    private void nextTurnHandler(IRoundManager roundManager, IGameMatch gameMatch) throws RemoteException {
        roundManager.nextTurn(gameMatch);
        notificarObservadores(EVENT.NEXT_TURN);
    }

    private void nextRoundHandler(IRoundManager roundManager, IGameMatch gameMatch) throws LostCardException, RemoteException {
        if(!gameMatch.getTurn().hasNextTurn()){
            roundManager.nextRound(gameMatch);
            notificarObservadores(EVENT.NEXT_ROUND);
        }
    }

    private void resetRoundHandler(IRoundManager roundManager, IGameMatch gameMatch) throws RemoteException {
        if(!gameMatch.getRound().hasNextRound()){
            if (!gameMatch.hasWinner()){
                roundManager.resetRound(gameMatch);
                notificarObservadores(EVENT.RESET_GAME);
            }
        }
    }

    private void hasWinnerHandler(IRoundManager roundManager, IGameMatch gameMatch) throws RemoteException {
        if(!gameMatch.getRound().hasNextRound()){
            if (gameMatch.hasWinner()){
                notificarObservadores(EVENT.WINNER);
            }
        }
    }
}