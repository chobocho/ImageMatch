package com.chobocho.mahjong;

import com.chobocho.mahjong.board.Board;

public interface BoardGame {
    int NONE_STATE = 0;
    int IDLE_STATE = 1;
    int PLAY_STATE = 2;
    int PAUSE_STATE = 3;
    int END_STATE = 4;
    int GAMEOVER_STATE = 5;

    public boolean idle();

    public boolean play();

    public boolean pause();

    public boolean winState();

    public boolean gameoverState();

    public void register(GameObserver observer);

    public boolean isPlayState();

    public boolean isFinishGame();

    public Board getBoard();

    public boolean tick();
    public boolean addTick(int t);
    public int getTime();
}
