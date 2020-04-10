package com.chobocho.mahjong;

public interface Score {
    public int addScore(int score);
    public void init();
    public boolean setScore(int score);
    public boolean setHighScore(int score);
    public void updateHighScore();
    public int getScore();
    public int getHighScore();
    public int calculatorScore(int removedBlockCount, int stage, int time);
}
