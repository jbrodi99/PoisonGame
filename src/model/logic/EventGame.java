package model.logic;

import model.enums.EVENT;
import model.interfaces.IEventGame;

import java.io.Serializable;

public class EventGame implements IEventGame, Serializable {
    private final EVENT event;
    private final int gameID;

    public EventGame(EVENT event, int gameID){
        this.event = event;
        this.gameID = gameID;
    }

    @Override
    public EVENT getEvent() {
        return event;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public boolean campare(int id) {
        return gameID == id;
    }
}
