package controller;

import model.interfaces.IPlayer;
import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;


public class AllPlayersConnectController extends SubController {
    public AllPlayersConnectController(IGameController controller) {
        super(controller);
    }

    @Override
    public void run() {
        try {

            //TODO: notificar a la vista para cambiar el panel

            controller.getView().play();

            IPlayerPublic player = getPlayerByID();
            IPlayerPublic currentPlayer = getCurrentPlayer();

            controller.getView().cleanBoard();

            if (isCurrentPlayer(currentPlayer)) {
                controller.getView().displayActions();
            } else {
                controller.getView().hiddenActions();
            }

            updateBoard();
            displayPlayerHand(player);
            displayPlayerGraveyard(player);

        } catch (RemoteException e) {
            controller.getView().displayMessage(e.getMessage());
        }
    }
}

