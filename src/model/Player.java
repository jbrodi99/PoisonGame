package model;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer{

    private int id;
    private String userName;
    private int health;
    private Boolean turn;
    private List<ICard> hand = new ArrayList<>();
    private List<ICard> graveyard = new ArrayList<>();

    public Player(Integer id, String userName, int health){
        this.id = id;
        this.userName = userName;
        this.health = health;
        this.turn = false;
    }

    @Override
    public void receiveCard(ICard card) {
        hand.add(card);
    }

    @Override
    public List<ICard> viewHand() {
        return new ArrayList<>(getHand());
    }

    @Override
    public ICard playCard(int index) {
        return getHand().remove(index);
    }

    @Override
    public Integer countPoison() {
        Integer poison = 0;
        for (ICard c : getGraveyard()){
            if(c.isType(TYPECARD.CUP)){
                poison++;
            }
        }
        return poison;
    }

    @Override
    public void receiveDamage(Integer poison) {
        if (getHealth() == 0) return;
        if (getHealth() <= poison){
            this.health = 0;
            return;
        }
        this.health -= poison;
        //(evento)
        //Notificar evento PLAYER_RECEIVE_DAMAGE
        //notificarObservadores(EVENT.PLAYER_RECEIVE_DAMAGE);
    }

    @Override
    public void takeHeap(CenterStack centerStack) {
        List<ICard> gr = getGraveyard();
        while(!centerStack.isEmpty()){
            gr.add(centerStack.removeTopCard());
        }
        //(event)
        //Notificar evento PLAYER_TAKE_HEAP
        //notificarObservadores(EVENT.PLAYER_TAKE_HEAP);
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setTurn(Boolean turn){
        this.turn = turn;
    }

    public Boolean getTurn() {
        return turn;
    }

    @Override
    public boolean hasID(int id) {
        return (getId().compareTo(id) == 0);
    }

    public List<ICard> getHand() {
        return hand;
    }

    @Override
    public List<ICard> getGraveyard() {
        return graveyard;
    }
}
