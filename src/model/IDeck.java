package model;

import java.util.List;

public interface IDeck {
    void shuffleDeck();
    Integer countCards();
    ICard removeTopCard();
    Boolean isEmpty();
    List<ICard> getCards();
}
