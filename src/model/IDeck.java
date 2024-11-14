package model;

import java.util.List;

public interface IDeck {
    public void shuffleDeck();
    public void cutDeck();
    public Integer countCards();
    public Card removeTopCard();
    public Boolean isEmpty();
}
