package com.chobocho.mahjong;

import com.chobocho.mahjong.board.Board;
import com.chobocho.mahjong.board.BoardImpl;
import com.chobocho.mahjong.board.InitBoardMethodImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    Board board;
    @Before
    public void setUp() throws Exception {
       board  = new BoardImpl(8, 12, 35);
    }

    @After
    public void tearDown() throws Exception {
        board = null;
    }

    @Test
    public void testInit() {

    }

    @Test
    public void testGetBoard() {
        System.out.println("Hi");
    }

    @Test
    public void testSetStage() {
    }

    @Test
    public void testRemoveBlock() {
        board  = new BoardImpl(8, 12, 35, new TestInitBoardMethodImpl());
        assertEquals(board.removeBlock(1, 0), 2);
    }

    @Test
    public void testImpossibleBoard() {
        board  = new BoardImpl(8, 12, 35, new TestImpssibleBoardMethodImpl());
        assertEquals(board.isRemovable(), false);
    }

    @Test
    public void testShuffleBoard() {
        board  = new BoardImpl(8, 12, 35, new TestImpssibleBoardMethodImpl());
        ((BoardImpl)board).SetInitMethod(new InitBoardMethodImpl());
        assertEquals(board.shuffle(), true);
    }
}