package model.logic;

import model.interfaces.ILog;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Log implements ILog, Serializable {

    private static int idGenerator = 0;
    private Map<String, Integer> players = new HashMap<>(
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
        return this.players.containsKey(userName);
    }

    @Override
    public int signUp(String userName) {
        if(isPlayer(userName)){
            return players.get(userName);
        }
        players.put(userName,idGenerator++);
        return players.get(userName);
    }

    @Override
    public int signIn(String userName) {
        if(!isPlayer(userName)) return -1;
        return players.get(userName);
    }

}
