package model.factorys;

import model.enums.NUMBER;
import model.interfaces.IRound;
import model.logic.Round;

import java.io.Serializable;

public class RoundFactory implements IRoundFactory, Serializable {
    @Override
    public IRound createRound(int numOfPlayers) {
        return new Round(NUMBER.values().length / numOfPlayers);
    }
}
