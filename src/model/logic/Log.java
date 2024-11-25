package model.logic;

import model.exceptions.NonExistsPlayerException;
import model.exceptions.PlayerAlreadyExistsException;
import model.interfaces.ILog;
import utils.Serializador;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Log implements ILog, Serializable {

    @Serial
    private final static String FILE_NAME_LOG = "data/Log.dat";
    private static int idGenerator = 1;
    private Map<String, Integer> players = null;
    private Serializador serializador = new Serializador(Log.FILE_NAME_LOG);

    private static ILog instance = null;

    public static ILog getInstance(){
        if (instance == null){
            instance = new Log();
        }
        return instance;
    }

    private Log(){
        Object[] os = (serializador.readObjects());
        players = (Map<String, Integer>) os[0];
        idGenerator = Integer.parseInt(os[1].toString());
    }

    @Override
    public boolean isPlayer(String userName) {
        return this.players.containsKey(userName);
    }

    @Override
    public int signUp(String userName) throws PlayerAlreadyExistsException {
        if(isPlayer(userName)){
            throw new PlayerAlreadyExistsException("The player already exists. Please Sign In or Used other name.");
        }
        players.put(userName,idGenerator++);
        serializador.writeOneObject(players);
        serializador.addOneObject(idGenerator);
        return players.get(userName);
    }

    @Override
    public int signIn(String userName) throws NonExistsPlayerException {
        if(!isPlayer(userName)){
            throw new NonExistsPlayerException("Non-existent player. Please Sign up previously");
        }
        return players.get(userName);
    }

}
