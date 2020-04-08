package com.chobocho.mahjong.state;

import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.board.Board;

abstract public class GameState {
    public boolean isIdleState() {
        return false;
    }

    public boolean isPlayState() {
        return false;
    }

    public boolean isPauseState() {
        return false;
    }

    public int getState() {
        return BoardGame.NONE_STATE;
    }

    public boolean isFinishGame() {
        return false;
    }

    public int getStage() { return 0; }

    public Board getBoard() {
        return null;
    }

}