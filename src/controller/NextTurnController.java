package controller;

import model.interfaces.IPlayer;
import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;


public class NextTurnController extends SubController{
    public NextTurnController(IGameController controller) {
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
