package com.chobocho.mahjong;

import com.chobocho.mahjong.board.InitBoardMethod;

public class TestInitBoardMethodImpl implements InitBoardMethod {
    @Override
    public int init(int[][] board, int width, int heigth) {
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }

        board[0][0] = 1;
        board[0][3] = 1;

        return 2;
    }
}
