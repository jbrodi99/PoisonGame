package model;

import java.util.List;

public interface IPlayer {
    void receiveCard(Card card);
    List<Card> viewHand();
    Card playCard(int index);
    Integer countPoison();
    void receiveDamage(Integer poison);
    void takeHeap(CenterStack centerStack);
    Integer getId();
    String getUserName();
    int getHealth();
    void setTurn(Boolean turn);
    Boolean getTurn();
    boolean hasID(int id);
    List<Card> getGraveyard();
}
