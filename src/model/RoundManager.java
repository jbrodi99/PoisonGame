package model;

import java.util.Queue;

public class RoundManager implements IRoundManager{

    private final IGameMatch gameMatch;
    private int turnsPlayed = 0;
    private int rounds;
    private int movesPerRound;
    private final int MOVES_PER_TURN = 4;

    public RoundManager(IGameMatch gameMatch) {
        this.gameMatch = gameMatch;
    }

    @Override
    public void nextTurn() {
        gameMatch.getQueueTurns().offer(gameMatch.getQueueTurns().poll());
        turnsPlayed++;
        //notificar Ronda jugada
        //notificarObservadores(EVENT.NEXT_TURN);
        /*Terminar de definir que acciones realizar desde el juego cuando se juega un turno*/
    }

    @Override
    public void nextRound() {
        if (rounds > 0) {
            turnsPlayed = 0;
            rounds--;
            dealHand();
            //notificar siguiente Ronda
            //notificarObservadores(EVENT.NEXT_ROUND);
        }
        //repetir hasta que solo quede un ganador...
    }

    @Override
    public int whoStart() {
        int firstTurn = 0;
        if(gameMatch.getNumOfPLayers() > 0){
            firstTurn = (int) (Math.random() * (gameMatch.getNumOfPLayers() - 1 + 1)) + 1;
            for (int i = 0; i < firstTurn - 1; i++) {
                gameMatch.getQueueTurns().offer(gameMatch.getQueueTurns().poll());
            }
        }
        return firstTurn;
    }

    @Override
    public boolean checkRound() {
        return turnsPlayed == movesPerRound;
    }

    @Override
    public void setRounds() {
        this.rounds = NUMBER.values().length / gameMatch.getNumOfPLayers();
    }

    @Override
    public void setMovesPerRound() {
        this.movesPerRound = MOVES_PER_TURN * gameMatch.getNumOfPLayers();
    }

    @Override
    public void dealHand() {
        Queue<IPlayer> queueTurns = gameMatch.getQueueTurns();
        for (int i = 0; i < gameMatch.getNumOfPLayers(); i++) {
            for (IPlayer p : queueTurns){
                p.receiveCard(gameMatch.getDeck().removeTopCard());
            }
        }
    }
}
