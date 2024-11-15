package model;

import java.util.Stack;

public interface ICenterStack {
    public void setValidator(IValidator validatorType);
    public Boolean isOverflowing();
    public Stack<Card> getCards();
    public Integer countCards();
    public void addCard(Card card);
    public Boolean isEmpty();
    public Card removeTopCard();
    public TYPECARD getTypecard();
}
