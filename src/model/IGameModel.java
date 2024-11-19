package model;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;

import java.rmi.RemoteException;

public interface IGameModel extends IModelAPI, IObservableRemoto {
    ILog getLog() throws RemoteException;
    IRanking getRanking() throws RemoteException;
    IGameMatch getGameMatch() throws RemoteException;
    void close(IObservadorRemoto obs, int playerID) throws RemoteException;
}