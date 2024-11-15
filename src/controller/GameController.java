package controller;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import model.EVENT;
import model.IGameModel;
import view.IView;

import java.rmi.RemoteException;

public class GameController implements IControladorRemoto {
    IView view;
    IGameModel model;

    public GameController(){
    }

    public void setView(IView view) {
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


}
