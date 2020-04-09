package com.chobocho.imagematch.ui.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.chobocho.imagematch.AndroidLog;
import com.chobocho.imagematch.BoardProfile;
import com.chobocho.imagematch.ui.DrawEngine;
import com.chobocho.imagematch.ui.DrawEngineImpl;
import com.chobocho.mahjong.BoardGame;


public class IdleDrawEngineImpl extends DrawEngineImpl implements DrawEngine {
    String TAG = getClass().getSimpleName();
    Paint paint = new Paint();

    @Override
    public void onDraw(Canvas g, BoardGame game, BoardProfile boardProfile, Bitmap[] blockImages, Bitmap[] buttonImages) {
        onDrawCommon(g, game, boardProfile, blockImages, buttonImages);
    }

    private void onDrawCommon(Canvas g, BoardGame game, BoardProfile boardProfile, Bitmap[] blockImages, Bitmap[] buttonImages) {
        int screenW = boardProfile.screenW;
        int screenH = boardProfile.screenH;

        int startX = boardProfile.buttonX;
        int startY = boardProfile.buttonY;

        int buttonGap = boardProfile.blockSize * 2;

        drawImage(g, buttonImages[BoardProfile.START_BUTTON], startX, startY + buttonGap, boardProfile.buttonW, boardProfile.buttonH, paint);

        AndroidLog.i(TAG, "Level: " + game.getStage());
        int stageX = boardProfile.startX + boardProfile.blockSize * 2;
        int stageY = boardProfile.startY + boardProfile.blockSize * 6;
        int stageNumSize = boardProfile.blockSize * 2;

        drawImage(g, buttonImages[game.getStage() / 10 + BoardProfile.SMALL_NUMBER_0], stageX, stageY, stageNumSize, stageNumSize, paint);
        drawImage(g, buttonImages[game.getStage() % 10 + BoardProfile.SMALL_NUMBER_0], stageX + stageNumSize, stageY, stageNumSize, stageNumSize, paint);
    }
}
