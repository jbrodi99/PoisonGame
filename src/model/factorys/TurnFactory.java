package model.factorys;

import model.interfaces.ITurn;
import model.logic.Turn;

import java.io.Serializable;
import java.util.LinkedList;

public class TurnFactory implements ITurnFactory, Serializable {
    @Override
    public ITurn createTurn(int numOfPlayers) {
        return new Turn(Turn.MOVES_PER_TURN * numOfPlayers, new LinkedList<>());
    }
}
