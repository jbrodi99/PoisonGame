package model;

import java.util.HashMap;
import java.util.Map;

public class Log implements ILog {

    private static int idGenerator = 0;
    private Map<String, Integer> jugadores = new HashMap<>(
            Map.of(
                    "juancito",1,
                    "pepe",2,
                    "luis",3,
                    "carlota",4,
                    "ana",5
            )
    );

    private static ILog instance = null;

    public static ILog getInstance(){
        if (instance == null){
            instance = new Log();
        }
        return instance;
    }

    private Log(){

    }

    @Override
    public boolean isPlayer(String userName) {
        return this.jugadores.containsKey(userName);
    }

    @Override
    public int signUp(String userName) {
        if(isPlayer(userName)){
            return jugadores.get(userName);
        }
        jugadores.put(userName,idGenerator++);
        return jugadores.get(userName);
    }

    @Override
    public int signIn(String userName) {
        if(!isPlayer(userName)) return -1;
        return jugadores.get(userName);
    }
}
