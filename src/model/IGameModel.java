package model;

public interface IGameModel extends IModelAPI {
    ILog getLog();
    IRanking getRanking();
    IGameMatch getGameMatch();
}