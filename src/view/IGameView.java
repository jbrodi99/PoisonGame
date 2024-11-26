package view;

import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayer;

import javax.swing.*;
import java.util.List;

public interface IGameView extends IView {
    void displayActions();
    void hiddenActions();
    //void displayCard(int number, String type);
    void displayHand(List<ICard> cards);
    void displayGraveyard(List<ICard> cards);
    void displayBoard(List<ICenterStack> centers, List<IPlayer> players);
    void displayTablePoints(Object o, int points);
    void cleanBoard();
    void finishGame(String message);
    void disconnect();
    void waitPlayer(int players);
    void backToMenu();
}
