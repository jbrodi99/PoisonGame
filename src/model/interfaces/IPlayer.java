package model.interfaces;

import model.enums.TYPECARD;
import model.exceptions.CardIndexOutOfBoundsException;

import java.util.List;

public interface IPlayer {

    //Getters
    int getHealth();
    Integer getId();
    String getUserName();
    List<ICard> getGraveyard();
    List<ICard> viewHand();

    //API
    void receiveHand(List<ICard> cards);
    void receiveDamage(Integer poison);
    void takeHeap(ICenterStack centerStack);
    void toggleTurn();
    int countPoison();
    boolean isYourTurn();
    boolean isAlive();
    boolean areYou(int id);
    ICard playCard(int index) throws CardIndexOutOfBoundsException;
    List<ICard> emptyYourGraveyard();
    TYPECARD kindOfCard(int index) throws CardIndexOutOfBoundsException;
}
