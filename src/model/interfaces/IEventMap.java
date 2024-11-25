package model.interfaces;

import model.enums.EVENT;

import java.util.EnumMap;

public interface IEventMap {
    public void register(EVENT event, Runnable action);
    public void trigger(EVENT event);
    public void unregister(EVENT event);
}

