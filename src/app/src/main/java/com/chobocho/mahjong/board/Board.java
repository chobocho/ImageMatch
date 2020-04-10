package com.chobocho.mahjong.board;

import com.chobocho.mahjong.state.PlayState;

public interface Board {
    public int[][] getBoard();
    public boolean isClear();
    public boolean isRemovable();
    public boolean needShuffle();
    public boolean shuffle();
    public void setStage(int stage);
    public int removeBlock(int x, int y);
    public void setPlayState(PlayState state);
}
