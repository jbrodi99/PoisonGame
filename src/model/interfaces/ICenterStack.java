package model.interfaces;

import model.enums.TYPECARD;

import java.util.List;
import java.util.Stack;

public interface ICenterStack {
    Boolean isOverflowing();
    Stack<ICard> getCards();
    void addCard(ICard card);
    TYPECARD getTypecard();
    List<ICard> emptyStack();
    ICard getTopCard();
    double countPoints();
    boolean isEmpty();
}
