package model.factorys;

import model.interfaces.IPlayer;

public interface IPlayerFactory {
    IPlayer createPlayer(String username, int id, int limitPoints);
}
