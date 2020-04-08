package com.chobocho.mahjong.state;

import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.board.Board;
import com.chobocho.mahjong.board.BoardImpl;
import com.chobocho.util.CLog;


public class PlayState extends MajhongGameState {
    final static String TAG = "PlayState";

    Board board;
    int boardWidth = 8;
    int boardHeigth = 12;
    int blockKind = 35;
    int stage = 0;

    public PlayState(int width, int height, int blockKind) {
        this.boardWidth = width;
        this.boardHeigth = height;
        this.blockKind = blockKind;

        initVars();
        initGame(stage);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void initVars() {
        board = new BoardImpl (boardWidth, boardHeigth, blockKind);
    }


    public void initGame(int stage) {
        this.stage = stage;
        initBoard();
    }

    private void initBoard() {
        board.setStage(stage);
    }

    public int getState() {
        return BoardGame.PLAY_STATE;
    }

    @Override
    public boolean isPlayState() {
        return true;
    }

    @Override
    public boolean isFinishGame() {
        return board.isClear();
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        return result.toString();
    }
    @Override
    public boolean removeBlock(int x, int y) {
        CLog.i(TAG, "removeBlock");

        boolean result = board.removeBlock(x, y);

        if (board.needShuffle()) {
            board.shuffle();
        }

        return result;

    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public int getStage() { return stage; }
}
