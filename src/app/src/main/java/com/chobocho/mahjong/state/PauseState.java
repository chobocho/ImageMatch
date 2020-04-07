package com.chobocho.mahjong.state;

import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.Mahjong;

public class PauseState extends MajhongGameState {
    public PauseState() { }

    public int getState() { return BoardGame.PAUSE_STATE; }
}