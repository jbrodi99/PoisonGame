package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck implements IDeck{

    private static IDeck instance = null;
    private List<ICard> cards = new ArrayList<>();

    public static IDeck getInstance(){
        if(instance == null){
            instance = new Deck();
            return instance;
        }
        return instance;
    }

    private Deck() {
        for (NUMBER n : NUMBER.values()){
            for (TYPECARD t : TYPECARD.values()){
                this.cards.add(new Card(n,t));
            }
        }
        this.shuffleDeck();
    }

    @Override
    public void shuffleDeck() {
        Collections.shuffle(getCards(),new Random(System.currentTimeMillis()));
    }

    public List<ICard> getCards() {
        return cards;
    }

    @Override
    public Integer countCards() {
        return getCards().size();
    }

    @Override
    public ICard removeTopCard() {
        return getCards().remove(0);
    }

    @Override
    public Boolean isEmpty() {
        return getCards().isEmpty();
    }
}
