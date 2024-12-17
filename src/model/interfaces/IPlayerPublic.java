package model.interfaces;

import java.util.List;

public interface IPlayerPublic {

    int getHealth();
    Integer getId();
    String getUserName();
    List<ICard> getGraveyard();
    List<ICard> viewHand();
    boolean isYourTurn();
    boolean isAlive();
    boolean areYou(int id);
}
