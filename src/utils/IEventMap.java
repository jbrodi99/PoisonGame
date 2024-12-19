package utils;

import model.enums.EVENT;
import model.interfaces.IEventGame;

public interface IEventMap {
    void register(EVENT event, Runnable action);
    void trigger(IEventGame event, int id);
}

