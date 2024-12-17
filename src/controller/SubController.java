package controller;

import model.interfaces.IPlayer;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayerPublic;

import java.rmi.RemoteException;
import java.util.List;

public abstract class SubController implements Runnable {
    protected IGameController controller;

    public SubController(IGameController controller) {
        this.controller = controller;
    }

    public IPlayerPublic getPlayerByID() throws  RemoteException  {
        return controller.getModel().getPlayerByID(controller.getCurrentGameID(),controller.getPlayerID());
    }

    public IPlayerPublic getCurrentPlayer() throws  RemoteException {
        return controller.getModel().getCurrentPlayer(controller.getCurrentGameID());
    }

    public boolean isCurrentPlayer(IPlayerPublic player) throws RemoteException {
        return player.areYou(controller.getPlayerID());
    }

    public void updateBoard() throws  RemoteException  {
        List<ICenterStack> centers = controller.getModel().getAllCenters(controller.getCurrentGameID());
        List<IPlayerPublic> players = controller.getModel().getAllPlayers(controller.getCurrentGameID());
        controller.getView().displayBoard(centers, players);
    }

    public void displayPlayerHand(IPlayerPublic player) throws RemoteException  {
        controller.getView().displayHand(player.viewHand());
    }

    public void displayPlayerGraveyard(IPlayerPublic player) throws  RemoteException  {
        controller.getView().displayGraveyard(player.getGraveyard());
    }
}
