package model.interfaces;

import model.exceptions.LostCardException;

import java.util.List;

public interface IGameMatch{

    //Getters
    IDeck getDeck();
    ITurn getTurn();
    IRound getRound();
    IPlayerGroup getPlayerGroup();
    ICenterStack getCenter(int pos);
    List<ICenterStack> getAllCenters();
    int getNumOfPLayers();
    int getLimitPoints();
    int getId();

    //API
    void startGame() throws LostCardException;
    void estimateDamage();
    boolean hasWinner();
    void endGame();
}
