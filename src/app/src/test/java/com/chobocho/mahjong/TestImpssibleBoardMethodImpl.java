package com.chobocho.mahjong;

import com.chobocho.mahjong.board.InitBoardMethod;

public class TestImpssibleBoardMethodImpl implements InitBoardMethod {
    @Override
    public int init(int[][] board, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }

        board[0][0] = 1;
        board[0][3] = 1;

        board[0][1] = 2;
        board[0][2] = 2;

        return 4;
    }
}
