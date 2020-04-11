package com.chobocho.mahjong.state;

import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.Score;
import com.chobocho.mahjong.board.Board;
import com.chobocho.mahjong.board.BoardImpl;
import com.chobocho.util.CLog;


public class PlayState extends MajhongGameState {
    final static String TAG = "PlayState";

    Board board;
    int boardWidth = 8;
    int boardHeigth = 12;
    int blockKind = 35;
    BoardGame game;
    Score score;

    public PlayState(BoardGame game, Score score, int width, int height, int blockKind) {
        this.boardWidth = width;
        this.boardHeigth = height;
        this.blockKind = blockKind;
        this.game = game;
        this.score = score;

        initVars();
        initGame(stage);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void initVars() {
        board = new BoardImpl (boardWidth, boardHeigth, blockKind);
        board.setPlayState(this);
    }


    public void initGame(int stage) {
        this.stage = stage > 99 ? 99 : stage;
        if (stage == 1) {
            score.init();
        }
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
    public void addTick(int tick) {
        game.addTick(tick);
    }

    @Override
    public boolean removeBlock(int x, int y) {
        CLog.i(TAG, "removeBlock");

        int result = board.removeBlock(x, y);

        if (board.needShuffle()) {
            board.shuffle();
        }

        if (result > 0) {
            game.addTick(result);
            int updateScore = score.calculatorScore(result, stage, game.getTime());
            score.addScore(updateScore);
            return true;
        } else {
            game.tick();
        }
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public int getStage() { return stage; }

}
