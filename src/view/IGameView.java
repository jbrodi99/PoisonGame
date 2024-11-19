package view;

import model.ICard;
import model.IPlayer;

public interface IGameView extends IView {
    void displayButton();
    void hiddenButton();
    void displayCard(ICard card);
    void displayHand(IPlayer player);
    void displayTable(Object o);
    void displayTablePoints(Object o, int points);
    void cleanTable();
    void finishGame(String message);
    void disconnect();
}
