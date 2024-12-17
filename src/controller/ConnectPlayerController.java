package controller;

import model.interfaces.IPlayer;
import java.rmi.RemoteException;

public class ConnectPlayerController extends SubController implements Runnable{

    public ConnectPlayerController(IGameController controller) {
        super(controller);
    }

    @Override
    public void run() {
        if (controller.getView() != null){
            controller.getView().cleanBoard();
            controller.getView().hiddenActions();
            controller.getView().waitPlayer(controller.getAllPlayers());
        }
    }
}
