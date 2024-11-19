package view;

import controller.GameController;
import model.ICard;
import model.IPlayer;

import javax.swing.*;

public class GraphicView extends JFrame implements IGameView {

    private GameController controller;
    private String userName;

    public GraphicView(String userName,GameController controller) {
        this.controller = controller;
        this.userName = userName;
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
    public void displayButton() {

    }

    @Override
    public void hiddenButton() {

    }

    @Override
    public void displayCard(ICard card) {

    }

    @Override
    public void displayHand(IPlayer player) {

    }

    @Override
    public void displayTable(Object o) {

    }

    @Override
    public void displayTablePoints(Object o, int points) {

    }

    @Override
    public void cleanTable() {

    }

    @Override
    public void finishGame(String message) {

    }

    @Override
    public void disconnect() {

    }
}
