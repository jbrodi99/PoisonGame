package utils;

import model.enums.EVENT;
import model.interfaces.IEventGame;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;


public class EventMap implements IEventMap, Serializable {

    private final EnumMap<EVENT, Runnable> eventMap;

    public EventMap() {
        eventMap = new EnumMap<>(EVENT.class);
    }

    // Asocia un evento a una acción
    @Override
    public void register(EVENT event, Runnable action) {
        if (event == null || action == null) {
            throw new IllegalArgumentException("El evento y la acción no pueden ser nulos.");
        }
        eventMap.put(event, action);
    }

    // Ejecuta la acción asociada a un evento
    @Override
    public void trigger(IEventGame event, int id) {
        Runnable action = eventMap.get(event.getEvent());
        if (action != null && event.campare(id)) {
            action.run();
        }
    }

}
