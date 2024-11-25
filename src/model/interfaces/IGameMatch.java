package model.interfaces;

import model.enums.STATUS;
import model.enums.TYPECARD;
import model.exceptions.InvalidLimitPointsException;
import model.exceptions.InvalidNumOfPlayerException;
import model.exceptions.LostCardException;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Queue;

public interface IGameMatch{
    ICenterStack getCenter(int pos);
    Queue<IPlayer> getQueueTurns();
    List<IPlayer> getPlayers();
    int getNumOfPLayers();
    void resetTurns();
    void resetRounds();
    void countTurn();
    void countRound();
    int getLimitPoints();
    int getMovesPerRound();
    int getTurnsPlayed();
    int getRoundsPlayed();
    int getRounds();
    void dealHand() throws LostCardException;
    void retrieveDeck();
    void estimateDamage();
    void startGame() throws RemoteException, LostCardException;
    void endGame() throws RemoteException;
    void initGame(int limitPoints, int numOfPlayers) throws RemoteException, InvalidLimitPointsException, InvalidNumOfPlayerException;
    int getConnectedPlayers() throws RemoteException;
    int getPlayersAlive() throws RemoteException;
    boolean hasWinner();
    IPlayer getCurrentPlayer() throws RemoteException;
    List<IPlayer> getAllPlayers() throws RemoteException;
    List<ICenterStack> getAllCenters() throws RemoteException;

}
