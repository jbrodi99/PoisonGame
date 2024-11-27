package model.logic;


import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import model.enums.EVENT;
import model.exceptions.*;
import model.interfaces.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;


public class GameModel extends ObservableRemoto implements IGameModel, Serializable {

    private static IGameModel instance = null;
    private final ILog log;
    private final IRanking ranking;
    private final IGameMatch gameMatch;
    private final IPlayerManager playerManager;
    private final IRoundManager roundManager;
    private final IGamePersistence gamePersistence;
    private boolean exists = false;

    private GameModel(){
        log = Log.getInstance();
        ranking = Ranking.getInstance();
        gameMatch = GameMatch.getInstance();
        playerManager = new PlayerManager();
        roundManager = new RoundManager();
        gamePersistence = new GamePersistence();
    }

    public static IGameModel getInstance() throws RemoteException {
        if(instance == null)   instance = new GameModel();
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
    public void close(IObservadorRemoto obs, int playerID) throws RemoteException {
        this.removerObservador(obs);
        playerManager.disconnectPlayer(gameMatch,playerID);
        notificarObservadores(EVENT.DISCONNECT_PLAYER);
    }

    @Override
    public void playTurn(int indexCard, int indexCenter) throws RemoteException, InvalidTypeCardException, LostCardException, CardIndexOutOfBoundsException {
        boolean valid = roundManager.checkMove(getGameMatch(),indexCard,indexCenter);
        if(!valid){
            throw new InvalidTypeCardException("Select the correct center");
        }
        roundManager.playTurn(getGameMatch(),indexCard,indexCenter);
        notificarObservadores(EVENT.PLAYER_PLAYED_CARD);
        if (roundManager.checkAndApplySanction(getGameMatch(),indexCenter)){
            notificarObservadores(EVENT.PLAYER_TAKE_HEAP);
        }
        boolean isNext = roundManager.nextTurn(getGameMatch());
        notificarObservadores(EVENT.NEXT_TURN);
        if(isNext){
            isNext = roundManager.nextRound(getGameMatch());
            if(isNext){
                notificarObservadores(EVENT.NEXT_ROUND);
            }
            else {
                //verificar si hay ganador
                if(gameMatch.hasWinner()){
                    notificarObservadores(EVENT.WINNER);
                } else {
                    notificarObservadores(EVENT.RESET_GAME);
                }
            }
        }

    }

    @Override
    public void initGame(int limitPoints, int numOfPlayers) throws RemoteException, InvalidLimitPointsException, InvalidNumOfPlayerException {
        getGameMatch().initGame(limitPoints,numOfPlayers);
        exists = true;
    }

    @Override
    public int signIn(String userName) throws RemoteException, NonExistsPlayerException {
        return getLog().signIn(userName);
    }

    @Override
    public void signUp(String userName) throws RemoteException, PlayerAlreadyExistsException {
        getLog().signUp(userName);
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
        boolean isConnect = false;
        List<IPlayer> players = gameMatch.getPlayers();
        for (IPlayer p : players){
            if(p.areYou(id)){
                isConnect = true;
            }
        }
        return isConnect;
    }

    @Override
    public IPlayer getCurrentPlayer() throws RemoteException {
        return gameMatch.getCurrentPlayer();
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
        return getGameMatch().getAllPlayers();
    }

    @Override
    public IPlayer getPlayerByID(int id) throws RemoteException {
        return playerManager.getPlayerByID(getGameMatch(),id);
    }

    @Override
    public boolean isExists() throws RemoteException{
        return exists;
    }
}