package model.logic;

import model.interfaces.IRound;

import java.io.Serializable;

public class Round implements IRound, Serializable {

    private final int rounds;
    private int roundsPlayed = 1;

    public Round (int rounds){
        this.rounds = rounds;
    }

    @Override
    public void countRound() {
        roundsPlayed++;
    }

    @Override
    public void resetRound() {
        roundsPlayed = 0;
    }

    @Override
    public boolean hasNextRound() {
        return roundsPlayed < rounds;
    }
}
