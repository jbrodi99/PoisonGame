package model.interfaces;


import model.logic.Ranking;

import java.util.List;
import java.util.Map;

public interface IRanking {
//    List<Map.Entry<String, Integer>> getTable();
    List<Ranking.SerializableEntry> getTopFivePlayers();
    void updateScore(String userName);
}
