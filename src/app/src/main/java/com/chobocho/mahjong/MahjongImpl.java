package com.chobocho.mahjong;

import com.chobocho.mahjong.board.Board;
import com.chobocho.mahjong.state.*;
import com.chobocho.util.CLog;

public class MahjongImpl extends BoardGameImpl implements Mahjong {
    private static final String TAG = "MahjongImpl";
    private static final String Version = "0.1105.TD1";

    CLog log;

    public MahjongImpl(CLog log, int width, int height, int blockKind) {
        this.log = log;
        noneState = new NoneState();
        idleState = new IdleState();
        playState = new PlayState(this, width, height, blockKind);
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

    private boolean setState(int newState) {
        log.i(TAG, "newState: " + newState);
        switch (newState) {
            case NONE_STATE:
                state = noneState;
                break;
            case IDLE_STATE:
                int stage = playState.getStage();
                if (state == pauseState || state == gameoverState) {
                    playState.initGame(1);
                } else {
                    playState.initGame(stage + 1);
                }
                leftTime = 11;
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

    public boolean isPlayState() {
        return state.isPlayState();
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
}
