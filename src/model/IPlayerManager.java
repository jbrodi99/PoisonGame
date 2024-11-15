package model;

import java.util.List;

public interface IPlayerManager extends IModelPlayerManager {
    IPlayer getPlayerByID(int id);
    List<IPlayer> getAllPlayers();
}
