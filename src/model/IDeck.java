package model;

import java.util.List;

public interface IDeck {
    void shuffleDeck();
    Integer countCards();
    Card removeTopCard();
    Boolean isEmpty();
    List<Card> getCards();
}
