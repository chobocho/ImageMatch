package com.chobocho.mahjong.state;

import com.chobocho.mahjong.BoardGame;

public class IdleState extends MajhongGameState {

    public IdleState() {
    }

    public int getState() { return BoardGame.IDLE_STATE; }
}