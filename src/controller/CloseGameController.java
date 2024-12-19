package controller;

public class CloseGameController extends SubController{

    public CloseGameController(IGameController controller) {
        super(controller);
    }

    @Override
    public void run() {
        controller.getView().displayMessage("A player has been disconnected.\n The progress of the game will be lost.");
        controller.getView().backToMenu();
    }
}
