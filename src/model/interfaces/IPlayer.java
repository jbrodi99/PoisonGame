package model.interfaces;

import model.enums.TYPECARD;
import model.exceptions.CardIndexOutOfBoundsException;

import java.util.List;

public interface IPlayer extends IPlayerPublic{

    //API
    void receiveHand(List<ICard> cards);
    void receiveDamage(Integer poison);
    void takeHeap(ICenterStack centerStack);
    void toggleTurn();
    int countPoison();

    ICard playCard(int index) throws CardIndexOutOfBoundsException;
    List<ICard> emptyYourGraveyard();
    TYPECARD kindOfCard(int index) throws CardIndexOutOfBoundsException;
}
