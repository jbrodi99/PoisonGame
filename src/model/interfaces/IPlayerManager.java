package model.interfaces;

import java.rmi.RemoteException;
import java.util.List;

public interface IPlayerManager {
    void connectPlayer(IGameMatch gameMatch,String userName, int id) throws RemoteException;
    void disconnectPlayer(IGameMatch gameMatch,int id) throws RemoteException;
    IPlayer getPlayerByID(IGameMatch gameMatch,int id) throws RemoteException;
    boolean isAllPlayersConnect(IGameMatch gameMatch) throws RemoteException;
    String getNamePlayers();
}
