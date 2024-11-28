package model.factorys;

import model.interfaces.IRound;
import model.interfaces.ITurn;

public interface ITurnFactory {
    ITurn createTurn(int numOfPlayers);
}

