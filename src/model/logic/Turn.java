package model.logic;

import model.interfaces.IPlayer;
import model.interfaces.ITurn;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;

public class Turn implements ITurn , Serializable {

    public static final int MOVES_PER_TURN = 4;
    private final int turnsPerRound;
    private int turnsPlayed;
    private final Queue<IPlayer> queueTurns;

    public Turn(int turnsPerRound, Queue<IPlayer> queueTurns){
        this.turnsPlayed = 0;
        this.turnsPerRound = turnsPerRound;
        this.queueTurns = queueTurns;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return queueTurns.peek();
    }

    @Override
    public void countTurn() {
        turnsPlayed++;
    }

    @Override
    public void resetTurns() {
        turnsPlayed = 0;
    }

    @Override
    public boolean hasNextTurn() {
        return turnsPlayed < turnsPerRound;
    }

    @Override
    public void next() {
        queueTurns.offer(queueTurns.poll());
    }

    @Override
    public List<IPlayer> getPlayersAlive() {
        return List.copyOf(queueTurns);
    }

    @Override
    public void addPLayer(IPlayer player) {
        queueTurns.offer(player);
    }

    @Override
    public void removePlayer(IPlayer player) {
        queueTurns.remove(player);
    }
}
