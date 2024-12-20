package controller;

public class CloseGameController extends SubController{

    public CloseGameController(IGameController controller) {
        super(controller);
    }

    @Override
    public void run() {
        controller.setCurrentGameID(0);
        controller.getView().displayMessage("“A player has been disconnected.\n The game progress will be saved. \nTo play again, all players must be connected”.");
        controller.getView().backToMenu();
    }
}
