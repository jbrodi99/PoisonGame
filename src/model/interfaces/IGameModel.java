package model.interfaces;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import model.exceptions.*;
import model.logic.Ranking;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IGameModel extends IObservableRemoto {

    int initGame(int limitPoints, int numOfPlayers, String gameName, String hostName) throws RemoteException, InvalidLimitPointsException, InvalidNumOfPlayerException, InvalidNumOfPlayerException;
    void playTurn(int gameID,int indexCard,int indexCenter) throws RemoteException, CardIndexOutOfBoundsException, InvalidTypeCardException, LostCardException;
    List<Ranking.SerializableEntry> getTopFive() throws RemoteException;
    IPlayerPublic getCurrentPlayer(int gameID) throws RemoteException;
    IPlayerPublic getPlayerByID(int gameID,int id) throws RemoteException;
    List<ICenterStack> getAllCenters(int gameID) throws RemoteException;
    List<IPlayerPublic> getAllPlayers(int gameID) throws RemoteException;
    int signIn(String userName) throws RemoteException, NonExistsPlayerException;
    void signUp(String userName) throws PlayerAlreadyExistsException, RemoteException;
    void connectPLayer(int gameID,String userName, int id) throws RemoteException, LostCardException, GameCompleteException;
//    void reconnectPLayer(IGameMatch match,int id) throws RemoteException;
    boolean isPlayerConnect(int gameID,int id) throws RemoteException;
    List<IGameMatchStatusPublic> findGames(String username) throws RemoteException;
    void loadGame(int gameID, int playerID) throws RemoteException;
    void saveGame(int gameID) throws RemoteException;
    void close(IObservadorRemoto obs,int gameID, int playerID) throws RemoteException;
    List<IGameMatchStatusPublic> getAllMatches() throws RemoteException;
}