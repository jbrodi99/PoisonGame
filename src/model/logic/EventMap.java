package model.logic;

import model.enums.EVENT;

import java.io.Serializable;
import java.util.EnumMap;

public class EventMap implements model.interfaces.IEventMap, Serializable {

    private final EnumMap<EVENT, Runnable> eventMap;

    public EventMap() {
        // Inicializa el mapa con el tipo del enum
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
    public void trigger(EVENT event) {
        Runnable action = eventMap.get(event);
        if (action != null) {
            action.run();
        } else {
            System.out.println("No hay acción registrada para el evento: " + event);
        }
    }

    // Elimina un evento registrado
    @Override
    public void unregister(EVENT event) {
        eventMap.remove(event);
    }
}
