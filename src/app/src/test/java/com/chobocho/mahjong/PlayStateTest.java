package com.chobocho.mahjong;

import com.chobocho.mahjong.board.Board;
import com.chobocho.mahjong.board.BoardImpl;
import com.chobocho.mahjong.state.PlayState;
import com.chobocho.util.CLog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayStateTest {
    PlayState playState;

    @Before
    public void setUp() throws Exception {
        int boardWidth = 8;
        int boardHeight = 12;
        int blockKind = 35;
        CLog log = new CLog();
        Score score = new ScoreImpl();
        BoardGame game = new MahjongImpl(log, score, boardWidth, boardHeight, blockKind);
        playState = new PlayState(game, score, boardWidth, boardHeight, blockKind);
    }

    @After
    public void tearDown() throws Exception {
        playState = null;
    }

    @Test
    public void initGame() {
    }

    @Test
    public void getState() {
    }

    @Test
    public void isPlayState() {
    }

    @Test
    public void isFinishGame() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void removeBlock() {
        Board board  = new BoardImpl(8, 12, 35, new TestInitBoardMethodImpl());
        playState.setBoard(board);
        playState.removeBlock(1, 0);
        assertEquals(playState.isFinishGame(), true);
    }

    @Test
    public void getBoard() {
    }
}