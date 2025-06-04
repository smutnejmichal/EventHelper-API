package com.michalovec.eventhelper.Managers;

public class TestManager {

    private boolean isGameRunning = false;

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning() {
        isGameRunning = !isGameRunning;
    }
}
