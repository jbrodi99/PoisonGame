package model.logic;

import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import model.interfaces.Command;
import model.enums.EVENT;
import model.enums.STATUS;
import model.exceptions.*;
import model.factorys.*;
import model.interfaces.*;
import utils.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class GameModel extends ObservableRemoto implements IGameModel, Serializable {

    private static IGameModel instance = null;
    private final ILog log;
    private final IRanking ranking;
    private final IGameMatchFactory gameMatchFactory;
    private final Map<Integer, IGameMatch> matches = new HashMap<>();
    private final IPlayerManager playerManager;
    private final IRoundManager roundManager;
    private final IGamePersistence gamePersistence;
    private final IIDGenerator generator;

    private GameModel(IGameMatchFactory gameMatchFactory) {
        log = Log.getInstance();
        ranking = Ranking.getInstance();
        playerManager = PlayerManager.getInstance(new PlayerFactory());
        roundManager = RoundManager.getInstance();
        gamePersistence = GamePersistence.getInstance();
        generator = GameIDGenerator.getInstance();
        this.gameMatchFactory = gameMatchFactory;
    }

    public static IGameModel getInstance(IGameMatchFactory gameMatchFactory) throws RemoteException {
        if (instance == null) instance = new GameModel(gameMatchFactory);
        return instance;
    }

    @Override
    public List<Map.Entry<String, Integer>> getRankingTable()throws RemoteException {
        return ranking.getTable();
    }

    @Override
    public List<Ranking.SerializableEntry> getTopFive() throws RemoteException{
        return ranking.getTopFivePlayers();
    }

    @Override
    public int signIn(String userName) throws RemoteException, NonExistsPlayerException {
        return log.signIn(userName);
    }

    @Override
    public void signUp(String userName) throws RemoteException, PlayerAlreadyExistsException {
        log.signUp(userName);
    }

    @Override
    public int initGame(int limitPoints, int numOfPlayers, String gameName, String hostName) throws RemoteException, InvalidLimitPointsException, InvalidNumOfPlayerException {
        IGameMatch gameMatch = gameMatchFactory.createGameMatch(
                new CenterFactory(),
                new TurnFactory(),
                new RoundFactory(),
                new PlayerGroup(),
                new Deck(new CardFactory()),
                new GameMatchStatus(generator.nextID(),gameName,hostName,numOfPlayers,limitPoints, STATUS.WAITING)
        );
        matches.put(gameMatch.getStatus().getId(),gameMatch);
        return gameMatch.getStatus().getId();
    }

    @Override
    public void close(IObservadorRemoto obs,int gameID, int playerID) throws RemoteException {
        if (matches.containsKey(gameID)){
            IGameMatch match = matches.get(gameID);
            playerManager.disconnectPlayer(match,playerID);
            matches.remove(gameID);
            notificarObservadores(new EventGame(EVENT.CLOSE_GAME,gameID));
        }
        this.removerObservador(obs);
    }

    @Override
    public void playTurn(int gameID,int indexCard, int indexCenter) throws RemoteException, CardIndexOutOfBoundsException, InvalidTypeCardException, LostCardException {
        IGameMatch match = matches.get(gameID);

        GameInvoker invoker = new GameInvoker();

        invoker.addCommand(() -> checkValidMoveHandler(roundManager,match,indexCard,indexCenter));
        invoker.addCommand(() -> playTurnHandler(roundManager,match,indexCard,indexCenter));
        invoker.addCommand(() -> checkAndApplySanction(roundManager,match,indexCenter));
        invoker.addCommand(() -> nextTurnHandler(roundManager,match));
        invoker.addCommand(() -> nextRoundHandler(roundManager,match));

        invoker.executeCommands();
    }

    @Override
    public void connectPLayer(int gameID,String userName, int id) throws RemoteException, LostCardException, GameCompleteException {
        IGameMatch match = matches.get(gameID);

        playerManager.connectPlayer(match,userName, id);
        notificarObservadores(new EventGame(EVENT.CONNECT_PLAYER, gameID));

        if(playerManager.isAllPlayersConnect(match)){
            match.getStatus().setStatus(STATUS.COMPLETE);
            match.startGame();
            notificarObservadores(new EventGame(EVENT.ALL_PLAYERS_CONNECT, gameID));
        }
    }

    @Override
    public boolean isPlayerConnect(int gameID,int id) throws RemoteException {
        IGameMatch match = matches.get(gameID);
        return match.getPlayerGroup().isPLayerConnect(playerManager.getPlayerByID(match,id));
    }

    @Override
    public IPlayerPublic getCurrentPlayer(int gameID) throws RemoteException {
        IGameMatch match = matches.get(gameID);
        return match.getTurn().getCurrentPlayer();
    }

    @Override
    public void loadGame() throws RemoteException {
        //gamePersistence.loadGame(gameMatch,playerManager);
//        notificarObservadores(EVENT.LOAD_GAME);
    }

    @Override
    public void saveGame() throws RemoteException {
        //gamePersistence.saveGame(gameMatch,playerManager);
//        notificarObservadores(EVENT.SAVE_GAME);
    }

    @Override
    public List<ICenterStack> getAllCenters(int gameID) throws RemoteException {
        IGameMatch match = matches.get(gameID);
        return match.getAllCenters();
    }

    @Override
    public List<IPlayerPublic> getAllPlayers(int gameID) throws RemoteException {
        IGameMatch match = matches.get(gameID);
        return new ArrayList<>(match.getTurn().getPlayersAlive());
    }

    @Override
    public IPlayerPublic getPlayerByID(int gameID,int id) throws RemoteException {
        IGameMatch match = matches.get(gameID);
        return playerManager.getPlayerByID(match,id);
    }

    @Override
    public List<IGameMatchStatusPublic> getAllMatches() throws RemoteException {
        return matches
                .values()
                .stream()
                .map(IGameMatch::getStatus)
                .collect(Collectors.toList());
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
        notificarObservadores(new EventGame(EVENT.PLAYER_PLAYED_CARD, gameMatch.getStatus().getId()));
    }

    private void nextTurnHandler(IRoundManager roundManager, IGameMatch gameMatch) throws RemoteException {
        roundManager.nextTurn(gameMatch);
        notificarObservadores(new EventGame(EVENT.NEXT_TURN, gameMatch.getStatus().getId()));
    }

    private void checkAndApplySanction(IRoundManager roundManager, IGameMatch gameMatch, int index) throws RemoteException {
        if (roundManager.checkSanction(gameMatch,index)){
            roundManager.applySanction(gameMatch,index);
        }
        notificarObservadores(new EventGame(EVENT.PLAYER_TAKE_HEAP, gameMatch.getStatus().getId()));
    }

    private void nextRoundHandler(IRoundManager roundManager, IGameMatch gameMatch) throws LostCardException, RemoteException {
        if(!gameMatch.getTurn().hasNextTurn() ) {
            if (gameMatch.getRound().hasNextRound()) {
                roundManager.nextRound(gameMatch);
                notificarObservadores(new EventGame(EVENT.NEXT_ROUND, gameMatch.getStatus().getId()));
            } else {
                    roundManager.resetRound(gameMatch);
                    if (!gameMatch.hasWinner()){
                        notificarObservadores(new EventGame(EVENT.RESET_GAME, gameMatch.getStatus().getId()));
                    } else {
                        ranking.updateScore(gameMatch.getWinner().getUserName());
                        notificarObservadores(new EventGame(EVENT.WINNER, gameMatch.getStatus().getId()));
                    }
            }
        }
    }
}