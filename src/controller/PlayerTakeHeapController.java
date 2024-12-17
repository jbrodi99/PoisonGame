package controller;

import model.interfaces.IPlayer;
import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;


public class PlayerTakeHeapController extends SubController{
    public PlayerTakeHeapController(IGameController controller) {
        super(controller);
    }

    @Override
    public void run() {
        try {
            IPlayerPublic player = getPlayerByID();
            controller.getView().cleanBoard();
            updateBoard();
            displayPlayerHand(player);
            displayPlayerGraveyard(player);
        } catch (RemoteException e) {
            controller.getView().displayMessage(e.getMessage());
        }
    }
}
