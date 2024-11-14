package model;

import java.util.Stack;

public class CenterStack implements ICenterStack {

    private final double MAX_CARDS_VALUE = 13.0;
    private IValidatorType validatorType;
    private Stack<Card> cards = new Stack<>();
    private TYPECARD typecard;

    public CenterStack(TYPECARD typecard, IValidatorType validatorType){
        this.typecard = typecard;
        setValidator(validatorType);
    }

    public void setValidator(IValidatorType validatorType){
        this.validatorType = validatorType;
    }

    public Boolean isOverflowing(){
        float count = 0;
        if(getCards().isEmpty()){
            return false;
        }
        for(Card c : getCards()){
            if(c.isMayorTen()){
                count += 0.5;
            }else{
                count += Integer.parseInt(c.getValue().toString());
            }
        }
        return count >= MAX_CARDS_VALUE;
    }

    public Stack<Card> getCards(){
        return cards;
    }

    public Integer countCards() {
        return getCards().size();
    }

    public void addCard(Card card){
        getCards().add(card);
    }

    public Boolean isEmpty() {
        return getCards().size() == 0;
    }

    public Card removeTopCard() {
        return getCards().pop();
    }

    @Override
    public TYPECARD getTypecard() {
        return this.typecard;
    }
}
