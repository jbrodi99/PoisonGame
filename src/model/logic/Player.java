package model.logic;

import model.enums.TYPECARD;
import model.exceptions.CardIndexOutOfBoundsException;
import model.interfaces.ICard;
import model.interfaces.ICenterStack;
import model.interfaces.IPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer, Serializable {

    private int id;
    private String userName;
    private int health;
    private boolean turn;
    private List<ICard> hand = new ArrayList<>();
    private List<ICard> graveyard = new ArrayList<>();

    public Player(Integer id, String userName, int health){
        this.id = id;
        this.userName = userName;
        this.health = health;
        this.turn = false;
    }

    @Override
    public void receiveHand(List<ICard> cards) {
        hand.addAll(cards);
    }

    @Override
    public List<ICard> viewHand() {
        return hand;
    }

    @Override
    public ICard playCard(int index) throws CardIndexOutOfBoundsException {
        try {
            return getHand().remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CardIndexOutOfBoundsException("non-existent card");
        }
    }

    @Override
    public int countPoison() {
        int poison = 0;
        for (ICard c : getGraveyard()){
            if(c.isType(TYPECARD.CUP)){
                poison++;
            }
        }
        return poison;
    }

    @Override
    public void receiveDamage(Integer poison) {
        this.health -= poison;
    }

    @Override
    public void takeHeap(ICenterStack centerStack) {
        graveyard.addAll(centerStack.emptyStack());
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void toggleTurn(){
        turn = !turn;
    }
    public List<ICard> getHand() {
        return hand;
    }

    @Override
    public List<ICard> getGraveyard() {
        return graveyard;
    }

    @Override
    public List<ICard> emptyYourGraveyard() {
        List<ICard> temp = new ArrayList<>(graveyard);
        graveyard.clear();
        return temp;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public boolean areYou(int id) {
        return id == getId();
    }

    @Override
    public TYPECARD kindOfCard(int index) throws CardIndexOutOfBoundsException {
        try {
            return hand.get(index).getTypeCard();
        } catch (IndexOutOfBoundsException e) {
            throw new CardIndexOutOfBoundsException("non-existent card");
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isYourTurn() {
        return turn;
    }
}
