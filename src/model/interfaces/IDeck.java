package model.interfaces;

import model.exceptions.LostCardException;

import java.util.List;

public interface IDeck {
    void shuffleDeck();
    List<ICard> getCards();
    void addCards(List<ICard> cards);
    List<ICard> removeFourCards() throws LostCardException;
}
