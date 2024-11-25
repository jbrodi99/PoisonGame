package model.logic;

import model.interfaces.IRanking;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Ranking implements IRanking, Serializable {
    private static IRanking instance = null;
    // attributes
    Map<String, Integer> score;

    //constructor singleton
    public static IRanking getInstance(){
        if(instance == null)   instance = new Ranking();
        return instance;
    }

    private Ranking(){
        this.score = new HashMap<>();
    }

    @Override
    public Map<String, Integer> getScore() {
        return score;
    }

    @Override
    public void updateScore(String userName) {
        getScore().replace(userName,getScore().get(userName) + 1);
    }
    //methods
}
