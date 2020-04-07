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


    abstract public boolean idle();

    abstract public boolean play();

    abstract public boolean pause();

    abstract public boolean winState();


    abstract public boolean isPlayState();

    abstract public boolean isFinishGame();

    abstract public Board getBoard();

    public void register(GameObserver observer) {
        this.observers.add(observer);
        observer.updateState(state.getState());
    }

    protected void notifyToOberver() {
        for (GameObserver observer: observers) {
            observer.updateState(state.getState());
        }
    }

}

