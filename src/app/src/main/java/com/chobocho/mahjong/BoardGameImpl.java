package com.chobocho.mahjong;

import com.chobocho.mahjong.board.Board;
import com.chobocho.mahjong.state.*;

import java.util.ArrayList;

abstract public class BoardGameImpl implements BoardGame {
    protected GameState state;
    protected NoneState noneState;
    protected IdleState idleState;
    protected PlayState playState;
    protected PauseState pauseState;
    protected EndState endState;
    protected GameoverState gameoverState;
    protected ArrayList<GameObserver> observers = new ArrayList<>();
    protected int leftTime;
    protected Score score;

    abstract public boolean idle();
    abstract public boolean newGame();

    abstract public boolean play();

    abstract public boolean pause();

    abstract public boolean winState();

    abstract public boolean tryAgin();


    abstract public boolean isIdleState();
    abstract public boolean isPlayState();
    abstract public boolean isPauseState();
    abstract public boolean isEndState();

    abstract public boolean isFinishGame();

    abstract public Board getBoard();

    @Override
    public int getState() {
        return state.getState();
    }

    public void register(GameObserver observer) {
        this.observers.add(observer);
        observer.updateState(state.getState());
    }

    protected void notifyToOberver() {
        for (GameObserver observer: observers) {
            observer.updateState(state.getState());
        }
    }

    abstract public boolean resumeGame();

    public boolean tick() {
        leftTime--;
        if (leftTime < 0) {
            leftTime = 0;
        }
        return leftTime > 0;
    }

    public boolean addTick(int t) {
        leftTime += t;

        if (leftTime < 10) {
            leftTime += 10;
        }

        if (leftTime > MAX_TIME) {
            leftTime = MAX_TIME;
        }
        return true;
    }

    public int getTime() {
        return leftTime;
    }

    abstract public int getStage();

    public int getScore() {
        return score.getScore();
    }
    public int getHighScore() {
        return score.getHighScore();
    }
    public boolean updateHint() { return state.updateHint(); }

    @Override
    public int getHint() {
        return score.getHint();
    }
}

