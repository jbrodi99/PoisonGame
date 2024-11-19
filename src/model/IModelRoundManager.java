package model;

import java.rmi.RemoteException;

public interface IModelRoundManager {
    void nextTurn() throws RemoteException;
    void nextRound() throws RemoteException;
    boolean checkRound() throws RemoteException;
}
