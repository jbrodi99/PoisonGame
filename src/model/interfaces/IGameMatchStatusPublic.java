package model.interfaces;

import model.enums.STATUS;

public interface IGameMatchStatusPublic {
    int getId();
    String getName();
    String getHostName();
    int getNumOfPLayers();
    int getLimitPoints();
    STATUS getStatus();
}
