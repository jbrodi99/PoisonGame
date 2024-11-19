package model;

import java.rmi.RemoteException;

public interface IGameManagement {
    void startGame() throws RemoteException;
    void endGame() throws RemoteException;
    void initGame(int limitPoints, int numOfPlayers) throws RemoteException;
    int getConnectedPlayers() throws RemoteException;
    int getPlayersAlive() throws RemoteException;
    IPlayer getCurrentPlayer() throws RemoteException;

}
