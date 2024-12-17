package controller;

import model.interfaces.IPlayer;
import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public class PlayerPlayedCardController extends SubController implements Runnable{

    public PlayerPlayedCardController(IGameController controller) {
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
