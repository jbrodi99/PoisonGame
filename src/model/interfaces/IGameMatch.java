package model.interfaces;

import model.exceptions.LostCardException;

import java.util.List;

public interface IGameMatch {

    //Getters
    IGameMatchStatus getStatus();
    IDeck getDeck();
    ITurn getTurn();
    IRound getRound();
    IPlayerGroup getPlayerGroup();
    ICenterStack getCenter(int pos);
    List<ICenterStack> getAllCenters();

    //API
    void startGame() throws LostCardException;
    void estimateDamage();
    void removeDeadPlayers();
    boolean hasWinner();
    IPlayer getWinner();
    void endGame();
}
