package controller;

import model.interfaces.IPlayer;
import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;


public class WinnerController extends SubController{
    public WinnerController(IGameController controller) {
        super(controller);
    }

    @Override
    public void run() {
        try {
            IPlayerPublic winner = getCurrentPlayer();
            controller.getView().cleanBoard();
            controller.getView().hiddenActions();
            controller.getView().backToMenu();
            controller.getView().finishGame("El jugador " + winner.getUserName() + " es el ganador!!!");
        } catch (RemoteException e) {
            controller.getView().displayMessage(e.getMessage());
        }
    }
}
