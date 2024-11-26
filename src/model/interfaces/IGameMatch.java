package model.interfaces;

import model.exceptions.InvalidLimitPointsException;
import model.exceptions.InvalidNumOfPlayerException;
import model.exceptions.LostCardException;

import java.util.List;
import java.util.Queue;

public interface IGameMatch{

    //Getters
    ICenterStack getCenter(int pos);
    Queue<IPlayer> getQueueTurns();
    List<IPlayer> getPlayers();
    IPlayer getCurrentPlayer();
    List<IPlayer> getAllPlayers();
    List<ICenterStack> getAllCenters();
    int getNumOfPLayers();
    int getLimitPoints();
    int getMovesPerRound();
    int getTurnsPlayed();
    int getRoundsPlayed();
    int getRounds();
    int getConnectedPlayers();
    int getPlayersAlive();

    //API
    void initGame(int limitPoints, int numOfPlayers) throws InvalidLimitPointsException, InvalidNumOfPlayerException;
    void startGame() throws LostCardException;
    void resetTurns();
    void resetRounds();
    void countTurn();
    void countRound();
    void dealHand() throws LostCardException;
    void retrieveDeck();
    void estimateDamage();
    boolean hasWinner();
    void endGame();
}
