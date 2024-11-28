package model.factorys;

import model.interfaces.IPlayer;
import model.logic.Player;

public class PlayerFactory implements IPlayerFactory{
    @Override
    public IPlayer createPlayer(String username, int id, int limitPoints) {
        return new Player(id,username,limitPoints);
    }
}
