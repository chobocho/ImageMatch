package com.chobocho.mahjong;

import com.chobocho.mahjong.board.InitBoardMethod;

public class Test5BlockBoradMethodImpl implements InitBoardMethod {
    @Override
    public int init(int[][] board, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }

        board[0][0] = 1;
        board[0][1] = 1;

        board[1][1] = 2;
        board[1][2] = 2;

        board[2][2] = 3;
        board[2][3] = 3;

        board[3][3] = 4;
        board[3][4] = 4;

        board[4][4] = 5;
        board[4][5] = 5;

        return 5;
    }
}

