package utils;

public class GameIDGenerator implements IIDGenerator{

    private final static String FILE_NAME_GID = "data/GameIDS.dat";
    private Serializador serializador = new Serializador(GameIDGenerator.FILE_NAME_GID);
    private static IIDGenerator instance = null;
    private int id = 0;

    public static IIDGenerator getInstance() {
        if(instance == null)    instance = new GameIDGenerator();
        return instance;
    }

    private GameIDGenerator(){
        Object oid = serializador.readFirstObject();
        id = (int) oid;
        System.out.println(id);
    }

    @Override
    public int nextID() {
        int temp = id++;
        serializador.writeOneObject(id);
        return temp;
    }
}
