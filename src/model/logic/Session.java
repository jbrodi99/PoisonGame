package model.logic;

import model.interfaces.ISession;

public class Session implements ISession {
    private String username;
    private int id;
    private boolean isPlaying;

    public Session(String username, int id) {
        this.username = username;
        this.id = id;
        this.isPlaying = false;
    }

    @Override
    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }
}
