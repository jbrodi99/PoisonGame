package model;

public interface IGameModel {
    ILog getLog();
    IRanking getRanking();
    IGameMatch getGameMatch();
    void initGame(int limitPoints, int numOfPlayers);
    void loadGame();
    void saveGame();
    void checkPlay();
    void endGame();
}

