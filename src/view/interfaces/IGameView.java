package view.interfaces;

import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayer;
import model.interfaces.IPlayerPublic;

import java.util.List;

public interface IGameView extends IView {
    void displayActions();
    void hiddenActions();
    void displayHand(List<ICard> cards);
    void displayGraveyard(List<ICard> cards);
    void displayBoard(List<ICenterStack> centers, List<IPlayerPublic> players);
    void displayTablePoints(Object o, int points);
    void cleanBoard();
    void finishGame(String message);
    void disconnect();
    void waitPlayer(List<IPlayerPublic> players);
    void backToMenu();
    void play();
    void chmod();
}
