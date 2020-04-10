package com.chobocho.mahjong;

public class ScoreImpl implements Score {
    int score = 0;
    int highScore = 0;

    public ScoreImpl() {
        this.score = 0;
        this.highScore = 0;
    }

    @Override
    public void init() {
        this.score = 0;
    }

    @Override
    public int addScore(int score) {
        this.score += score;
        updateHighScore();
        return this.score;
    }

    @Override
    public boolean setScore(int score) {
        this.score = score;
        return true;
    }

    @Override
    public boolean setHighScore(int score) {
        this.highScore = score;
        return true;
    }

    @Override
    public void updateHighScore() {
        if (this.score > this.highScore) {
            this.highScore = this.score;
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getHighScore() {
        return highScore;
    }

    public int calculatorScore(int removedBlockCount, int stage, int time) {
        return 0;
    }
}

