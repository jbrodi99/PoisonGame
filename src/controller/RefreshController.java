package controller;

import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;

public class RefreshController extends SubController{


    public RefreshController(IGameController controller) {
        super(controller);
    }

    @Override
    public void run() {
        try {
            IPlayerPublic player = getPlayerByID();
            IPlayerPublic currentPlayer = getCurrentPlayer();
            if (currentPlayer.areYou(controller.getPlayerID())) {
                controller.getView().displayActions();
            } else {
                controller.getView().hiddenActions();
            }
            controller.getView().cleanBoard();
            updateBoard();
            displayPlayerHand(player);
            displayPlayerGraveyard(player);
        } catch (RemoteException e) {
            controller.getView().displayMessage(e.getMessage());
        }
    }
}
