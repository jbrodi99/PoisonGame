package view;

import controller.GameController;
import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayer;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class GraphicView extends JFrame implements IGameView {

    private GameController controller;

    public GraphicView(GameController controller) {
        this.controller = controller;
        setTitle("Poison Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1200,650,400,400);
        setResizable(true);
    }

    @Override
    public void displayMessage(String message) {

    }

    @Override
    public void init() {
        if(!isVisible()){
            setVisible(true);
        }
    }

    @Override
    public JPanel getPanel() {
        return null;
    }

    @Override
    public void displayActions() {

    }

    @Override
    public void hiddenActions() {

    }

    @Override
    public void displayHand(List<ICard> cards) {

    }

    @Override
    public void displayGraveyard(List<ICard> cards) {

    }

    @Override
    public void displayBoard(List<ICenterStack> cards, List<IPlayer> players) {

    }

    @Override
    public void waitPlayer(int players) {

    }

    @Override
    public void displayTablePoints(Object o, int points) {

    }

    @Override
    public void cleanBoard() {

    }

    @Override
    public void finishGame(String message) {

    }

    @Override
    public void disconnect() {

    }
}
