package model.interfaces;

import model.enums.STATUS;

public interface IGameMatchStatus extends IGameMatchStatusPublic{
    void setStatus(STATUS status);
}
