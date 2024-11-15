package model;

public interface IGameModel {
    ILog getLog();
    IRanking getRanking();
    IGameMatch getGameMatch();
    void startGame();
    void loadGame();
    void saveGame();
    void endGame();
}