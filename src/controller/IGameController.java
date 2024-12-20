package controller;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import model.interfaces.IGameModel;
import model.interfaces.IPlayerPublic;
import view.interfaces.IGameView;
import view.interfaces.IView;

import java.util.List;

public interface IGameController {
    IGameView getView();
    IGameModel getModel();
    int getPlayerID();
    int getCurrentGameID();
    void setCurrentGameID(int newGame);
    List<IPlayerPublic> getAllPlayers();
}
