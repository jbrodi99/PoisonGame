package model.interfaces;

import model.logic.GameMatch;

import java.util.Map;

public interface IRanking {
    Map<String, Integer> getScore();
    void updateScore(String userName);
}
