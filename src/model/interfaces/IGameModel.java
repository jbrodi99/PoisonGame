package model.interfaces;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import model.exceptions.*;

import java.rmi.RemoteException;
import java.util.List;

public interface IGameModel extends IObservableRemoto {

    //APIs

    //Game logic
    int initGame(int limitPoints, int numOfPlayers) throws RemoteException, InvalidLimitPointsException, InvalidNumOfPlayerException;
    void playTurn(int indexCard,int indexCenter) throws Exception;
    boolean isExists() throws RemoteException;

    //Getters
    IPlayer getCurrentPlayer() throws RemoteException;
    IPlayer getPlayerByID(int id) throws RemoteException;
    List<ICenterStack> getAllCenters() throws RemoteException;
    List<IPlayer> getAllPlayers() throws RemoteException;

    //connection management
    int signIn(String userName) throws RemoteException, NonExistsPlayerException;
    void signUp(String userName) throws PlayerAlreadyExistsException, RemoteException;
    void connectPLayer(String userName, int id) throws RemoteException, LostCardException;
    boolean isPlayerConnect(int id) throws RemoteException;

    //APIs para implementar para el final
    void loadGame() throws RemoteException;
    void saveGame() throws RemoteException;
    void close(IObservadorRemoto obs, int playerID) throws RemoteException;
}