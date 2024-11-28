package model.logic;

import model.enums.TYPECARD;
import model.interfaces.ICard;
import model.interfaces.ICenterStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CenterStack implements ICenterStack, Serializable {

    private final int MAX_CARDS_VALUE = 130;
    private final Stack<ICard> cards = new Stack<>();
    private final TYPECARD typecard;

    public CenterStack(TYPECARD typecard){
        this.typecard = typecard;
    }

    public Boolean isOverflowing(){
        return countPoints() * 10 >= MAX_CARDS_VALUE;
    }

    public Stack<ICard> getCards(){
        return cards;
    }

    public void addCard(ICard card) {
        getCards().add(card);
    }

    @Override
    public TYPECARD getTypecard() {
        return this.typecard;
    }

    @Override
    public List<ICard> emptyStack() {
        List<ICard> temp = new ArrayList<>(cards);
        cards.clear();
        return temp;
    }

    @Override
    public ICard getTopCard() {
        return cards.peek();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public double countPoints() {
        double count = 0;
        if(getCards().isEmpty())    return 0;
        for(ICard c : getCards()){
            if(c.isMayorTen())      count += 5;
            else                    count += Integer.parseInt(c.getValue().toString()) * 10;
        }
        return count / 10;
    }
}
