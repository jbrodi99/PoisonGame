package model.interfaces;

import java.util.List;

public interface ITurn {
    IPlayer getCurrentPlayer();
    List<IPlayer> getPlayersAlive();
    void addPLayer(IPlayer player);
    void removePlayer(IPlayer player);
    void countTurn();
    void resetTurns();
    void next();
    boolean hasNextTurn();
}
