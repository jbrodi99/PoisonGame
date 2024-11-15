package model;

public interface IRoundManager extends IModelRoundManager {
    void dealHand();
    int whoStart();
    boolean checkRound();
    void setRounds();
    void setMovesPerRound();
}
