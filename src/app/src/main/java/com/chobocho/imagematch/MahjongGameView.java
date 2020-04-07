package com.chobocho.imagematch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;


import com.chobocho.imagematch.ui.DrawEngineManager;
import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.command.CommandEngine;
import com.chobocho.mahjong.command.CommandFactory;

import static android.content.Context.MODE_PRIVATE;

public class MahjongGameView extends View {
    private String LOG_TAG = this.getClass().getName();
    private Context mContext;

    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private boolean isSetScale = false;
    private boolean needScaleCanvas = false;

    BoardProfile boardProfile;

    BoardGame game;
    CommandEngine cmdEngine;
    CommandFactory commandFactory;
    DrawEngineManager drawEngine;
    DrawEngineManager gameDrawEngine;
    DrawEngineManager configDrawEngine;

    Bitmap[] blockImages;
    Bitmap[] buttonImages;

    public MahjongGameView(Context context, BoardGame game, BoardProfile boardProfile, CommandEngine cmdEngine) {
        super(context);
        this.mContext = context;
        this.boardProfile = boardProfile;

        loadImage();

        this.game = game;
        this.cmdEngine = cmdEngine;
        needScaleCanvas = false;
        commandFactory = new AndroidCommandFactory(boardProfile);

        gameDrawEngine = new GameDrawEngineManagerImpl(game, boardProfile, this);
        configDrawEngine = new ConfigDrawEngineManagerImpl(boardProfile, this);
        drawEngine = gameDrawEngine;
    }


    public void startGame() {

    }

    public void pauseGame() {

    }

    public void resumeGame() {

    }

    public void update() {
        Log.d(LOG_TAG, "View.update()");
        invalidate();
    }


    public void onDraw(Canvas canvas) {
        if (game == null) {
            return;
        }

        if (!isSetScale) {
            scaleX = canvas.getWidth() / 1080f;
            scaleY = canvas.getHeight() / 1920f;
            isSetScale = true;

            if (scaleX <= 0.999f) {
                needScaleCanvas = true;
                Log.d(LOG_TAG, "Resolution of device is smaller than 1080");
            }
        }

        if (needScaleCanvas) {
            canvas.scale(scaleX, scaleY);
        }

        drawEngine.onDraw(canvas, blockImages, buttonImages);
    }

    private void loadImage() {
        blockImages = new Bitmap[boardProfile.imageName.length+1];
        for (int i = 0; i < boardProfile.imageName.length; i++) {
            blockImages[i] = BitmapFactory.decodeResource(mContext.getResources(), boardProfile.imageName[i]);
        }
    }

}
