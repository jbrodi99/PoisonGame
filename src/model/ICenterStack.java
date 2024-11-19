package model;

import java.util.Stack;

public interface ICenterStack {
    public void setValidator(IValidator validatorType);
    public Boolean isOverflowing();
    public Stack<ICard> getCards();
    public Integer countCards();
    public void addCard(ICard ICard);
    public Boolean isEmpty();
    public ICard removeTopCard();
    public TYPECARD getTypecard();
}
