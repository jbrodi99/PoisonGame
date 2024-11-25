package model.logic;

import model.enums.TYPECARD;
import model.interfaces.*;

import java.io.Serializable;
import java.rmi.RemoteException;

public class RoundManager implements IRoundManager, Serializable {

    public static final int MOVES_PER_TURN = 4;

    @Override
    public boolean checkMove(IGameMatch gameMatch, int indexCard, int indexCenter) throws RemoteException {
        TYPECARD typeCenter = gameMatch.getCenter(indexCenter).getTypecard();
        TYPECARD typeCardUsed = gameMatch.getCurrentPlayer().kindOfCard(indexCard);
        return typeCenter.compareType(typeCardUsed);
    }

    @Override
    public TYPECARD playTurn(IGameMatch gameMatch, int indexCard, int center) throws RemoteException {
        ICard cardPlayed = gameMatch.getCurrentPlayer().playCard(indexCard);
        gameMatch.getCenter(center).addCard(cardPlayed);
        return cardPlayed.getTypeCard();
    }

    /*
    * true -> siguiente ronda
    * */
    @Override
    public boolean nextTurn(IGameMatch gameMatch) throws RemoteException {
        gameMatch.getCurrentPlayer().toggleTurn();
        gameMatch.getQueueTurns().offer(gameMatch.getQueueTurns().poll());
        gameMatch.getCurrentPlayer().toggleTurn();
        gameMatch.countTurn();
        return !hasNextTurn(gameMatch);
    }

    /*
    * true -> evento siguiente ronda
    * false -> evento reset game*/
    @Override
    public boolean nextRound(IGameMatch gameMatch) throws RemoteException {
        gameMatch.resetTurns();
        if (hasNextRound(gameMatch)) {
            gameMatch.countRound();
            gameMatch.dealHand();
            return true;
        }
        gameMatch.resetRounds();
        gameMatch.estimateDamage();
        gameMatch.retrieveDeck();
        gameMatch.dealHand();
        return false;
    }

    @Override
    public boolean checkAndApplySanction(IGameMatch gameMatch, int indexCenter) throws RemoteException {
        ICenterStack centerUsed = gameMatch.getCenter(indexCenter);
        if(!centerUsed.isOverflowing())  return false;
        gameMatch.getCurrentPlayer().takeHeap(gameMatch.getCenter(indexCenter));
        return true;
    }

    private boolean hasNextTurn(IGameMatch gameMatch) {
        return gameMatch.getTurnsPlayed() < gameMatch.getMovesPerRound();
    }

    private boolean hasNextRound(IGameMatch gameMatch) {
        return gameMatch.getRoundsPlayed() < gameMatch.getRounds();
    }
}
