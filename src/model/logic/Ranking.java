package model.logic;

import model.interfaces.IRanking;
import utils.Serializador;

import java.io.Serializable;
import java.util.*;

public class Ranking implements IRanking, Serializable {
    private static IRanking instance = null;
    private final Serializador serializador = new Serializador("out/artifacts/Veneno_jar/data/ranking.dat");
    private final Map<String, Integer> rankingTable;


    public static IRanking getInstance(){
        if(instance == null)   instance = new Ranking();
        return instance;
    }

    private Ranking(){
        Object or = serializador.readFirstObject();
        rankingTable = (Map<String, Integer>) or;
    }

//    @Override
//    public List<Map.Entry<String, Integer>> getTable() {
////        return rankingTable.entrySet().stream()
////                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
////                .toList();
//        return new ArrayList<>(rankingTable.entrySet().stream()
//                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
//                .toList());
//    }

    @Override
    public List<SerializableEntry> getTopFivePlayers() {
        return rankingTable.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(entry -> new SerializableEntry(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public void updateScore(String userName) {
        if (rankingTable.containsKey(userName)){
            rankingTable.put(userName, rankingTable.get(userName) + 1);
        } else {
            rankingTable.put(userName, 1);
        }
        serializador.writeOneObject(rankingTable);
    }

    public static class SerializableEntry implements Serializable {
        private final String key;
        private final Integer value;

        public SerializableEntry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }
}
