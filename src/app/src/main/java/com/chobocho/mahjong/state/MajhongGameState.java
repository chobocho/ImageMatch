package com.chobocho.mahjong.state;

public class MajhongGameState extends GameState {
    protected int stage = 0;
    public boolean removeBlock(int x, int y) {
        return false;
    }
    public boolean tick() { return true; }
    public void addTick(int tick) { };
    public int getStage() { return stage; }
}
