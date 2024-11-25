package model.interfaces;

import model.enums.TYPECARD;
import model.exceptions.InvalidTypeCardException;

import java.util.List;
import java.util.Stack;

public interface ICenterStack {
    Boolean isOverflowing();
    Stack<ICard> getCards();
    void addCard(ICard ICard);
    TYPECARD getTypecard();
    List<ICard> emptyStack();
    ICard getTopCard();
    double countPoints();
    boolean isEmpty();
}
