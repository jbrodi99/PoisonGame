package controller;


import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;


public class ResetGameController extends SubController{
    public ResetGameController(IGameController controller) {
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
