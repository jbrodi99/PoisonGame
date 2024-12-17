package model.interfaces;

import model.enums.EVENT;

public interface IEventGame {
    EVENT getEvent();
    int getGameID();
    boolean campare(int id);
}
