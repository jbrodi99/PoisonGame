package model;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.rmi.RemoteException;
import java.util.List;

public class GameModel extends ObservableRemoto implements IGameModel {

    private static IGameModel instance = null;
    private ILog log;
    private IRanking ranking;
    private IGameMatch gameMatch;

    private GameModel(){
        //instanciar log, ranking y partida, pero creo que se deberían recuperar con objetos serializados, chequear...
        log = Log.getInstance();
        ranking = Ranking.getInstance();
        gameMatch = GameMatch.getInstance();
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
        this.disconnectPlayer(playerID);
    }


    @Override
    public void startGame() throws RemoteException {
        getGameMatch().startGame();
    }

    @Override
    public void endGame() throws RemoteException {
        getGameMatch().endGame();
    }

    @Override
    public void initGame(int limitPoints, int numOfPlayers) throws RemoteException {
        getGameMatch().initGame(limitPoints,numOfPlayers);
    }

    @Override
    public int getConnectedPlayers() throws RemoteException {
        return getGameMatch().getConnectedPlayers();
    }

    @Override
    public IPlayer getCurrentPlayer() throws RemoteException {
        return null;
    }
    
    @Override
    public void connectPlayer(String userName, int id, int health) throws RemoteException {
        getGameMatch().connectPlayer(userName,id,health);
    }

    @Override
    public void disconnectPlayer(int id) throws RemoteException {
        getGameMatch().disconnectPlayer(id);
    }

    @Override
    public boolean isAllPlayersConnect() throws RemoteException {
        return getGameMatch().isAllPlayersConnect();
    }

    @Override
    public IPlayer getPlayerByID(int id) throws RemoteException {
        return getGameMatch().getPlayerByID(id);
    }

    @Override
    public List<IPlayer> getAllPlayers() throws RemoteException {
        return getGameMatch().getAllPlayers();
    }

    @Override
    public void nextTurn() throws RemoteException {
        getGameMatch().nextTurn();
        //notificar evento
    }

    @Override
    public void nextRound() throws RemoteException {
        getGameMatch().nextRound();
        //notificar ronda
    }

    @Override
    public boolean checkRound() throws RemoteException {
        return getGameMatch().checkRound();
    }
}