package model.logic;

import model.enums.TYPECARD;
import model.exceptions.CardIndexOutOfBoundsException;
import model.factorys.IRoundFactory;
import model.factorys.ITurnFactory;
import model.factorys.RoundFactory;
import model.interfaces.*;

import java.io.Serializable;

public class RoundManager implements IRoundManager, Serializable {

    private static IRoundManager instance = null;

    public static IRoundManager getInstance() {
        if(instance == null)    instance = new RoundManager();
        return instance;
    }

    @Override
    public boolean checkValidMove(IGameMatch gameMatch, int indexCard, int indexCenter) throws CardIndexOutOfBoundsException {
        TYPECARD typeCenter = gameMatch
                .getCenter(indexCenter)
                .getTypecard();
        TYPECARD typeCardUsed = gameMatch
                .getTurn()
                .getCurrentPlayer()
                .kindOfCard(indexCard);
        return typeCenter.compareType(typeCardUsed);
    }

    @Override
    public void playTurn(IGameMatch gameMatch, int indexCard, int center) throws CardIndexOutOfBoundsException {
        gameMatch
                .getCenter(center)
                .addCard(gameMatch
                        .getTurn()
                        .getCurrentPlayer()
                        .playCard(indexCard)
                );
    }

    @Override
    public void nextTurn(IGameMatch gameMatch){
        gameMatch
                .getTurn()
                .getCurrentPlayer()
                .toggleTurn();
        gameMatch
                .getTurn()
                .next();
        gameMatch
                .getTurn()
                .getCurrentPlayer()
                .toggleTurn();
        gameMatch.getTurn()
                .countTurn();
    }

    @Override
    public void nextRound(IGameMatch gameMatch) {
        gameMatch.getTurn().resetTurns();
        gameMatch.getRound().countRound();
        gameMatch.getDeck().dealHand(gameMatch.getTurn().getPlayersAlive());
    }

    @Override
    public void resetRound(IGameMatch gameMatch) {
        gameMatch.getTurn().resetTurns();
        gameMatch.getRound().resetRound();
        gameMatch.estimateDamage();
        gameMatch.getDeck().retrieveDeck(
                gameMatch
                        .getTurn()
                        .getPlayersAlive(),
                gameMatch.getAllCenters()
                );
        for (IPlayer player : gameMatch.getTurn().getPlayersAlive()) {
            System.out.println(player.getId());
        }
        gameMatch.removeDeadPlayers();
        System.out.println("se reseteo el juego");
        gameMatch.getDeck().dealHand(
                gameMatch
                        .getTurn()
                        .getPlayersAlive()
                );

    }

    @Override
    public boolean checkSanction(IGameMatch gameMatch, int indexCenter){
        return gameMatch.getCenter(indexCenter).isOverflowing();
    }

    @Override
    public void applySanction(IGameMatch gameMatch, int indexCenter) {
        gameMatch
                .getTurn()
                .getCurrentPlayer()
                .takeHeap(
                        gameMatch.getCenter(indexCenter)
                );
    }
}
