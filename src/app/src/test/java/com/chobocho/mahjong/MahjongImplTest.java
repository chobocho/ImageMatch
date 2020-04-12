package com.chobocho.mahjong;

import com.chobocho.util.CLog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MahjongImplTest {
    BoardGame game;

    @Before
    public void setUp() throws Exception {
        int boardWidth = 8;
        int boardHeight = 12;
        int blockKind = 35;
        CLog log = new CLog();
        GameInfo gameInfo = new GameInfoImpl();
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
    }

    @Test
    public void winState() {
    }

    @Test
    public void gameoverState() {
    }

    @Test
    public void idle() {
        assertEquals(game.idle(), true);
    }

    @Test
    public void tryAgin() {
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
}