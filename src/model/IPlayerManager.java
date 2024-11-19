package model;

import java.rmi.RemoteException;
import java.util.List;

public interface IPlayerManager {
    void connectPlayer(String userName, int id, int health) throws RemoteException;
    void disconnectPlayer(int id) throws RemoteException;
    boolean isAllPlayersConnect() throws RemoteException;
    IPlayer getPlayerByID(int id) throws RemoteException;
    List<IPlayer> getAllPlayers() throws RemoteException;
}
