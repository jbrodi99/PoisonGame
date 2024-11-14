package model;

public class Ranking implements IRanking{
    private static IRanking instance = null;
    // attributes

    //constructor singleton
    public static IRanking getInstance(){
        if(instance == null)   instance = new Ranking();
        return instance;
    }

    private Ranking(){

    }

    //methods

}
