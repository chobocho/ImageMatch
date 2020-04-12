package com.chobocho.mahjong;

public interface GameInfo {
    public int addScore(int score);
    public void init();
    public boolean setScore(int score);
    public boolean setHighScore(int score);
    public void updateHighScore();
    public int getScore();
    public int getHighScore();
    public int calculatorScore(int removedBlockCount, int time);
    public void backup();
    public void revert();
    public int getStage();
    public void setStage(int stage);
    public void stageUp();
    public int getHint();
    public int setHint(int hint);
    public int addHint(int hint);
    public void initTime();
    public int getTime();
    public int setMaxTime(int newMaxTime);
    public boolean addTick(int t);
    public boolean tick();
}
