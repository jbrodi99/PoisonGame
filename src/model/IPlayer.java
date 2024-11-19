package model;

import java.util.List;

public interface IPlayer {
    void receiveCard(ICard ICard);
    List<ICard> viewHand();
    ICard playCard(int index);
    Integer countPoison();
    void receiveDamage(Integer poison);
    void takeHeap(CenterStack centerStack);
    Integer getId();
    String getUserName();
    int getHealth();
    void setTurn(Boolean turn);
    Boolean getTurn();
    boolean hasID(int id);
    List<ICard> getGraveyard();
}
