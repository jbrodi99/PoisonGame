package utils;

public class UserIDGenerator implements IIDGenerator {

    private final static String FILE_NAME_UID = "data/UserIDS.dat";
    private Serializador serializador = new Serializador(UserIDGenerator.FILE_NAME_UID);
    private static IIDGenerator instance = null;
    private int id = 0;

    public static IIDGenerator getInstance() {
        if(instance == null)    instance = new UserIDGenerator();
        return instance;
    }

    private UserIDGenerator(){
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
