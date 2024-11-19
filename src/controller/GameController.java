package controller;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import model.EVENT;
import model.IGameModel;
import view.IGameView;

import java.rmi.RemoteException;

public class GameController implements IControladorRemoto {
    private IGameView view;
    private IGameModel model;
    private int playerID;

    public GameController(){
    }

    public void setView(IGameView view) {
        this.view = view;
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T model) throws RemoteException {
        this.model = (IGameModel) model;
    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object eventDetected) throws RemoteException {
        EVENT event = (EVENT) eventDetected;
        //solucionar el tema de usar muchos if

    }

    public int getPlayerID() {
        return playerID;
    }

    public void disconnectPlayer() {
        try {
            model.close(this, getPlayerID());
        }catch (RemoteException e){
            view.displayMessage("mistake has occurred.");
        }
    }

}
