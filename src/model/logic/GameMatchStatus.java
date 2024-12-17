package model.logic;

import model.enums.STATUS;
import model.interfaces.IGameMatchStatus;

import java.io.Serializable;

public class GameMatchStatus implements IGameMatchStatus, Serializable {
    private final int id;
    private final String name;
    private final String hostname;
    private final int numOfPlayers;
    private final int limitPoints;
    private STATUS status;

    public GameMatchStatus(int id, String name, String hostname, int numOfPlayers,int limitPoints, STATUS status) {
        this.id = id;
        this.name = name;
        this.hostname = hostname;
        this.numOfPlayers = numOfPlayers;
        this.limitPoints = limitPoints;
        this.status = status;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getHostName() {
        return hostname;
    }

    @Override
    public int getNumOfPLayers() {
        return numOfPlayers;
    }

    @Override
    public int getLimitPoints() {
        return limitPoints;
    }

    @Override
    public STATUS getStatus() {
        return status;
    }

    @Override
    public void setStatus(STATUS status) {
        this.status = status;
    }
}
