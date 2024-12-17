package controller;

import model.interfaces.IGameModel;
import model.interfaces.IPlayerPublic;
import view.interfaces.IGameView;
import view.interfaces.IView;

import java.util.List;

public interface IGameController {
    IView getPopup();
    IGameView getView();
    IGameModel getModel();
    int getPlayerID();
    int getCurrentGameID();
    List<IPlayerPublic> getAllPlayers();
}
