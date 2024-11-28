package model.interfaces;

import java.util.List;

public interface IPlayerGroup {
    List<IPlayer> getPlayers();
    void addPlayer(IPlayer player);
    void removePlayer(IPlayer player);
    boolean isPLayerConnect(IPlayer player);
    int howManyPlayersAreOnline();
}
