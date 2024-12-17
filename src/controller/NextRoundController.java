package controller;

import model.interfaces.IPlayer;
import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NextRoundController extends SubController{
    public NextRoundController(IGameController controller) {
        super(controller);
    }

    @Override
    public void run() {
        try {
            IPlayerPublic iam = getPlayerByID();
            controller.getView().cleanBoard();
            updateBoard();
            displayPlayerHand(iam);
            displayPlayerGraveyard(iam);
        } catch (RemoteException e) {
            controller.getView().displayMessage(e.getMessage());
        }
    }
}
