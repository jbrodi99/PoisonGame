package model.logic;

import model.exceptions.NonExistsPlayerException;
import model.exceptions.PlayerAlreadyExistsException;
import model.interfaces.ILog;

import utils.IIDGenerator;
import utils.Serializador;
import utils.UserIDGenerator;

import java.io.Serializable;
import java.util.Map;

public class Log implements ILog, Serializable {

    private final static String FILE_NAME_LOG = "out/artifacts/Veneno_jar/data/Log.dat";
    private final IIDGenerator generator = UserIDGenerator.getInstance();
    private final Map<String, Integer> players;
    private final Serializador serializador = new Serializador(Log.FILE_NAME_LOG);

    private static ILog instance = null;

    public static ILog getInstance(){
        if (instance == null){
            instance = new Log();
        }
        return instance;
    }

    private Log(){
        //reescribir log por ids repetidos luego de crear el generador de ids
        Object[] os = (serializador.readObjects());
        players = (Map<String, Integer>) os[0];
    }

    @Override
    public boolean isPlayer(String userName) {
        return this.players.containsKey(userName);
    }

    @Override
    public void signUp(String userName) throws PlayerAlreadyExistsException {
        if(isPlayer(userName)){
            throw new PlayerAlreadyExistsException("The player already exists. Please Sign In or Used other name.");
        }
        players.put(userName,generator.nextID());
        serializador.writeOneObject(players);
        players.get(userName);
    }

    @Override
    public int signIn(String userName) throws NonExistsPlayerException {
        if(!isPlayer(userName)){
            throw new NonExistsPlayerException("Non-existent player. Please Sign up previously");
        }
        return players.get(userName);
    }
}
