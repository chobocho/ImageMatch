package com.chobocho.mahjong.state;

import com.chobocho.mahjong.BoardGame;

public class EndState extends MajhongGameState {

    public EndState() {

    }

    public int getState() { return BoardGame.END_STATE; }

}