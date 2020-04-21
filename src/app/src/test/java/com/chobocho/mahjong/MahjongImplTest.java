package com.chobocho.mahjong;

import com.chobocho.util.CLog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MahjongImplTest {
    BoardGame game;
    GameInfo gameInfo;

    @Before
    public void setUp() throws Exception {
        int boardWidth = 8;
        int boardHeight = 12;
        int blockKind = 35;
        CLog log = new CLog();
        gameInfo = new GameInfoImpl();
        game = new MahjongImpl(log, gameInfo, boardWidth, boardHeight, blockKind);
        assert game != null;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void play() {
    }

    @Test
    public void pause() {
        assertEquals(false, game.isPauseState());
        game.pause();
        assertEquals(true, game.isPauseState());
    }

    @Test
    public void winState() {
        assertEquals(true, game.idle());
        int stage = game.getStage();
        assertEquals(2, stage);
        assertEquals(true, ((MahjongImpl)game).setState(BoardGame.END_STATE));
        assertEquals(3, gameInfo.getHighStage());
    }

    @Test
    public void gameoverState() {
    }

    @Test
    public void idle() {
        assertEquals(true, game.isIdleState());
        assertEquals(1, game.getStage());
    }

    @Test
    public void tryAgain() {
    }

    @Test
    public void isPlayState() {
    }

    @Test
    public void isFinishGame() {
    }

    @Test
    public void removeBlock() {
        //board  = new BoardImpl(8, 12, 35, new TestImpssibleBoardMethodImpl());

    }

    @Test
    public void getBoard() {
    }

    @Test
    public void scoreTest() {
        gameInfo.setHighScore(10000000);
        assertEquals(9999999, gameInfo.getHighScore());
    }

    @Test
    public void stageTest() {
        gameInfo.setHighStage(10000000);
        assertEquals(999, gameInfo.getHighStage());
    }

}