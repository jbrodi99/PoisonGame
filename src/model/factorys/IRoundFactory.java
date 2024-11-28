package model.factorys;

import model.interfaces.IRound;

public interface IRoundFactory {
    IRound createRound(int numOfPlayers);
}
