package model;

import java.util.List;

public interface IPlayer {
    public void receiveCard(Card card);
    public List<Card> viewHand();
    public Card playCard(int index);
    public Integer countPoison();
    public void receiveDamage(Integer poison);
    public void takeHeap(CenterStack centerStack);
    public Integer getId();
    public String getUserName();
    public int getHealth();
    public void setTurn(Boolean turn);
    public Boolean getTurn();
}
