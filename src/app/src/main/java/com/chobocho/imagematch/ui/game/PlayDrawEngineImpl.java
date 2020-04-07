package com.chobocho.imagematch.ui.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.chobocho.imagematch.BoardProfile;
import com.chobocho.imagematch.ui.DrawEngine;
import com.chobocho.imagematch.ui.DrawEngineImpl;
import com.chobocho.mahjong.BoardGame;


public class PlayDrawEngineImpl extends DrawEngineImpl implements DrawEngine {
    Paint paint = new Paint();

    @Override
    public void onDraw(Canvas g, BoardGame game, BoardProfile boardProfile, Bitmap[] blockImages, Bitmap[] buttonImages) {
        onDrawCommon(g, game, boardProfile, blockImages, buttonImages);
    }

    private void onDrawCommon(Canvas g,  BoardGame game,BoardProfile boardProfile, Bitmap[] blockImages, Bitmap[] buttonImages) {
        int screenW = g.getWidth();
        int screenH = g.getHeight();

        int startX = boardProfile.startX;
        int startY = boardProfile.startY;
        int h = boardProfile.boardHeight;
        int w = boardProfile.boardWidth;
        int imgSize = boardProfile.blockSize;

        int[][] board = game.getBoard().getBoard();

         for (int i = 0; i < h; i++) {
             for (int j = 0; j < w; j++) {
                 drawImage(g, blockImages[board[i][j]], startX + j * imgSize, startY + i * imgSize, imgSize, imgSize, paint);
             }
         }

        drawImage(g, buttonImages[boardProfile.PAUSE_BUTTON], screenW-imgSize-20, screenH-imgSize-20, imgSize, imgSize, paint);
    }
}
