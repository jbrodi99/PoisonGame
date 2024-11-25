package model.interfaces;

import model.enums.TYPECARD;

import java.util.List;

public interface IPlayer {
    void receiveHand(List<ICard> cards);
    int getHealth();
    List<ICard> viewHand();
    ICard playCard(int index);
    Integer countPoison();
    void receiveDamage(Integer poison);
    void takeHeap(ICenterStack centerStack);
    Integer getId();
    String getUserName();
    void toggleTurn();
    List<ICard> getGraveyard();
    List<ICard> emptyYourGraveyard();
    boolean isAlive();
    boolean areYou(int id);
    TYPECARD kindOfCard(int index);
}
