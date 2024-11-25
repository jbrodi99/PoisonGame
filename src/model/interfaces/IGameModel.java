package model.interfaces;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import model.exceptions.*;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IGameModel extends IObservableRemoto {
    ILog getLog() throws RemoteException;
    IRanking getRanking() throws RemoteException;
    IGameMatch getGameMatch() throws RemoteException;
    void close(IObservadorRemoto obs, int playerID) throws RemoteException;
    void playTurn(int indexCard,int indexCenter) throws RemoteException, InvalidTypeCardException, LostCardException, CardIndexOutOfBoundsException;
    void initGame(int limitPoints, int numOfPlayers) throws RemoteException, InvalidLimitPointsException, InvalidNumOfPlayerException;
    void startGame() throws RemoteException, LostCardException;
    void connectPLayer(String userName,int id) throws RemoteException, LostCardException;
    IPlayer getCurrentPlayer() throws RemoteException;
    void loadGame() throws RemoteException;
    void saveGame() throws RemoteException;
    List<ICenterStack> getAllCenters() throws RemoteException;
    List<IPlayer> getAllPlayers() throws RemoteException;
    IPlayer getPlayerByID(int id) throws RemoteException;
}