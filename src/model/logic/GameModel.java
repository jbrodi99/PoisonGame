package model.logic;


import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import com.sun.jdi.event.ExceptionEvent;
import model.enums.EVENT;
import model.enums.TYPECARD;
import model.exceptions.InvalidLimitPointsException;
import model.exceptions.InvalidNumOfPlayerException;
import model.exceptions.InvalidTypeCardException;
import model.interfaces.*;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;


public class GameModel extends ObservableRemoto implements IGameModel, Serializable {

    private static IGameModel instance = null;
    private ILog log;
    private IRanking ranking;
    private IGameMatch gameMatch;
    private IPlayerManager playerManager;
    private IRoundManager roundManager;
    private IGamePersistence gamePersistence;

    private GameModel(){
        //instanciar log, ranking y partida, pero creo que se deberían recuperar con objetos serializados, chequear...
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

    @Override
    public ILog getLog() throws RemoteException {
        return log;
    }

    @Override
    public IRanking getRanking() throws RemoteException {
        return ranking;
    }

    @Override
    public IGameMatch getGameMatch() throws RemoteException {
        return gameMatch;
    }

    @Override
    public void close(IObservadorRemoto obs, int playerID) throws RemoteException {
        this.removerObservador(obs);
        playerManager.disconnectPlayer(gameMatch,playerID);
        notificarObservadores(EVENT.DISCONNECT_PLAYER);
    }

    @Override
    public void playTurn(int indexCard, int indexCenter) throws RemoteException, InvalidTypeCardException {
        boolean valid = roundManager.checkMove(getGameMatch(),indexCard,indexCenter);
        if(!valid){
            throw new InvalidTypeCardException("jugada invalida ");     //TODO: mejorar mensaje...
        }
        TYPECARD typeUsed = roundManager.playTurn(getGameMatch(),indexCard,indexCenter);
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
    }

    @Override
    public void startGame() throws RemoteException {
        if(playerManager.isAllPlayersConnect(getGameMatch())){
            getGameMatch().startGame();
        }
    }

    @Override
    public void connectPLayer(String userName, int id) throws RemoteException {
        playerManager.connectPlayer(getGameMatch(),userName,id);
        if(playerManager.isAllPlayersConnect(getGameMatch())){
            startGame();
            notificarObservadores(EVENT.ALL_PLAYERS_CONNECT);
        }
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
}