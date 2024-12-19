package model.logic;

import model.enums.NUMBER;
import model.enums.TYPECARD;
import model.factorys.ICardFactory;
import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IDeck;
import model.interfaces.IPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck implements IDeck, Serializable {

    private final List<ICard> cards = new ArrayList<>();

    public Deck(ICardFactory cardFactory) {
        for (NUMBER n : NUMBER.values()){
            for (TYPECARD t : TYPECARD.values()){
                this.cards.add(cardFactory.createCard(n,t));
            }
        }
        shuffleDeck();
    }

    @Override
    public void dealHand(List<IPlayer> players) {
        for (IPlayer p : players){
            p.receiveHand(new ArrayList<>(cards.subList(0,4)));
            cards.subList(0,4).clear();
        }
    }

    @Override
    public void retrieveDeck(List<IPlayer> players, List<ICenterStack> centers) {
        for (IPlayer p : players){
            cards.addAll(p.emptyYourGraveyard());
        }
        for (ICenterStack c : centers){
            cards.addAll(c.emptyStack());
        }
        shuffleDeck();
    }

    @Override
    public void shuffleDeck() {
        Collections.shuffle(cards,new Random(System.currentTimeMillis()));
    }

}
