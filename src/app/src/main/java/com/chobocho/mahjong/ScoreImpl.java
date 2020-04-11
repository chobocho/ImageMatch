package com.chobocho.mahjong;

public class ScoreImpl implements Score {
    int score = 0;
    int highScore = 0;
    int previousScore = 0;
    protected int stage = 0;
    int hint = 0;
    final int DEFAULT_HINT = 3;

    final int MAX_HINT = 5;

    public ScoreImpl() {
        this.score = 0;
        this.highScore = 0;
        this.previousScore = 0;
        this.stage = 0;
        this.hint = DEFAULT_HINT;
    }

    @Override
    public void init() {
        this.score = 0;
        this.previousScore = 0;
        this.hint = DEFAULT_HINT;
    }

    @Override
    public int getStage() {
        return stage;
    }

    @Override
    public void setStage(int newStage) {
        newStage = newStage < 1 ? 1 : newStage;

        this.stage = newStage > 99 ? 99 : newStage;
        if (newStage == 1) {
            init();
        } else {
            backup();
        }
        this.stage = newStage;
    }

    @Override
    public void stageUp() {
        this.stage = (this.stage + 1) % 100;

        if ((stage % 3) == 2) {
            addHint(1);
        }

        backup();
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

    public int calculatorScore(int removedBlockCount, int time) {
        return 0;
    }

    @Override
    public void revert() {
        score = previousScore;
    }

    @Override
    public void backup() {
        previousScore = score;
    }

    @Override
    public int getHint() {
        return hint;
    }

    @Override
    public int setHint(int hint) {
        if (hint < 0) {
            return 0;
        }
        this.hint = hint >= MAX_HINT ? MAX_HINT : hint;
        return hint;
    }

    @Override
    public int addHint(int h) {
        this.hint += h;
        this.hint = this.hint > MAX_HINT ? MAX_HINT : this.hint;
        this.hint = this.hint < 0 ? 0 : this.hint;
        return this.hint;
    }
}

