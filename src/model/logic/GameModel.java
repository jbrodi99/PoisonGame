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
            if (getPlayerByID(gameID,playerID).isAlive()){
                playerManager.disconnectAllPlayers(match);
                saveGame(gameID);
                matches.remove(gameID);
                notificarObservadores(new EventGame(EVENT.CLOSE_GAME,gameID));
            } else {
                playerManager.disconnectPlayer(match,playerID);
            }
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


    private void reconnectPLayer(IGameMatch match,int playerID) throws RemoteException {
        for (IPlayer player : match.getTurn().getPlayersAlive()) {
            if (player.areYou(playerID)){
                playerManager.reconnectPlayer(match,player);
            }
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
    public List<IGameMatchStatusPublic> findGames(String username) throws RemoteException {
        return gamePersistence.findGames(username);
    }

    @Override
    public void loadGame(int gameID, int playerID) throws RemoteException {
        IGameMatch match = gamePersistence.loadGame(gameID);
        if (match != null){
            matches.put(match.getStatus().getId(),match);
            reconnectPLayer(match,playerID);
            notificarObservadores(new EventGame(EVENT.LOAD_GAME,gameID));
            if (match.getPlayerGroup().howManyPlayersAreOnline() == match.getTurn().getPlayersAlive().size()){
                match.getStatus().setStatus(STATUS.COMPLETE);
                notificarObservadores(new EventGame(EVENT.ALL_PLAYERS_CONNECT, match.getStatus().getId()));
            }
        }
    }

    @Override
    public void saveGame(int gameID) throws RemoteException {
        if (matches.containsKey(gameID)){
            IGameMatch match = matches.get(gameID);
            match.getStatus().setStatus(STATUS.WAITING);
            gamePersistence.saveGame(match,playerManager.getNamePlayers(match));
        }
    }

    @Override
    public List<ICenterStack> getAllCenters(int gameID) throws RemoteException {
        IGameMatch match = matches.get(gameID);
        return match.getAllCenters();
    }

    @Override
    public List<IPlayerPublic> getAllPlayers(int gameID) throws RemoteException {
        IGameMatch match = matches.get(gameID);
        return new ArrayList<>(match.getPlayerGroup().getPlayers());
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