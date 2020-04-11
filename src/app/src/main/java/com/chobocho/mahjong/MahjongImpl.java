package com.chobocho.mahjong;

import com.chobocho.mahjong.board.Board;
import com.chobocho.mahjong.state.*;
import com.chobocho.util.CLog;

public class MahjongImpl extends BoardGameImpl implements Mahjong {
    private static final String TAG = "MahjongImpl";
    private static final String Version = "0.1105.TD1";
    boolean tryAgain = false;

    CLog log;

    public MahjongImpl(CLog log, Score score, int width, int height, int blockKind) {
        this.log = log;
        this.score = score;
        noneState = new NoneState();
        idleState = new IdleState();
        playState = new PlayState(this, this.score, width, height, blockKind);
        pauseState = new PauseState();
        endState = new EndState();
        gameoverState = new GameoverState();

        setState(BoardGame.IDLE_STATE);
    }

    public boolean play() {
        return setState(PLAY_STATE);
    }

    public boolean pause() {
        return setState(PAUSE_STATE);
    }

    public boolean winState() {
        return setState(END_STATE);
    }

    @Override
    public boolean gameoverState() {
        return setState(GAMEOVER_STATE);
    }

    public boolean idle() {
        return setState(IDLE_STATE);
    }

    @Override
    public boolean newGame() {
        tryAgain = false;
        idle();
        score.setStage(1);
        return true;
    }

    @Override
    public boolean resumeGame() {
        tryAgain = true;
        return idle();
    }

    @Override
    public boolean tryAgin() {
        tryAgain = true;
        return setState(IDLE_STATE);
    }

    private boolean setState(int newState) {
        log.i(TAG, "newState: " + newState);
        switch (newState) {
            case NONE_STATE:
                state = noneState;
                break;
            case IDLE_STATE:
                if (tryAgain) {
                    score.revert();
                    log.i(TAG, "IDLE_STATE try again " + score.getStage());
                    playState.initGame();
                } else if (state == pauseState || state == gameoverState) {
                    score.setStage(1);
                    log.i(TAG, "IDLE_STATE GameeOver " + score.getStage());
                    playState.initGame();
                } else {
                    score.stageUp();
                    log.i(TAG, "IDLE_STATE New game " + score.getStage());
                    playState.initGame();
                }
                tryAgain = false;
                leftTime = BoardGame.MAX_TIME + 1;
                state = idleState;
                break;
            case PLAY_STATE:
                state = playState;
                break;
            case PAUSE_STATE:
                state = pauseState;
                break;
            case END_STATE:
                state = endState;
                break;
            case GAMEOVER_STATE:
                state = gameoverState;
                break;
            default:
                break;
        }
        notifyToOberver();
        return true;
    }

    @Override
    public boolean isIdleState() {
        return state.isIdleState();
    }

    public boolean isPlayState() {
        return state.isPlayState();
    }

    @Override
    public boolean isPauseState() {
        return state.isPauseState();
    }

    @Override
    public boolean isEndState() {
        return state.isEndState();
    }

    public boolean isFinishGame() {
        return state.isFinishGame();
    }

    public boolean removeBlock(int x, int y) {
        log.i(TAG, "removeBlock " + x + ", " + y);
        if (state == playState) {
            return ((PlayState) state).removeBlock(x, y);
        }
        return false;
    }

    @Override
    public Board getBoard() {
        return state.getBoard();
    }

    @Override
    public int getStage() {
        return score.getStage();
    }
}
