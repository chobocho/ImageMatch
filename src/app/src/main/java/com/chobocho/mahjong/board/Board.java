package com.chobocho.mahjong.board;

public interface Board {
    public int[][] getBoard();
    public boolean isClear();
    public boolean isRemovable();
    public boolean needShuffle();
    public boolean shuffle();
    public void setStage(int stage);
    public boolean removeBlock(int x, int y);
}
