package com.chobocho.imagematch;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.chobocho.imagematch.BoardProfile;
import com.chobocho.imagematch.MahjongGameView;
import com.chobocho.imagematch.ui.DrawEngine;
import com.chobocho.imagematch.ui.DrawEngineManager;
import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.GameObserver;

class ConfigDrawEngineManagerImpl implements DrawEngineManager, GameObserver {
    final String TAG = this.getClass().getSimpleName();
    BoardGame game;
    BoardProfile boardProfile;
    MahjongGameView parent;

    private DrawEngine drawEngine;
    private DrawEngine commonDrawEngine;
    private DrawEngine[] boardDrawEngines;
    Bitmap[] blockImages;
    Bitmap[] buttonImages;

    public ConfigDrawEngineManagerImpl(BoardProfile boardProfile, MahjongGameView parent) {
        this.game = game;
        this.boardProfile = boardProfile;
        this.parent = parent;
/*
        this.commonDrawEngine = new CommonDrawEngineImpl();
        boardDrawEngines = new DrawEngine[BoardGame.END_STATE + 1];
        boardDrawEngines[BoardGame.NONE_STATE] = new NoneDrawEngineImpl();
        boardDrawEngines[BoardGame.IDLE_STATE] = new IdleDrawEngineImpl();
        boardDrawEngines[BoardGame.PLAY_STATE] = new PlayDrawEngineImpl();
        boardDrawEngines[BoardGame.PAUSE_STATE] = new PauseDrawEngineImpl();
        boardDrawEngines[BoardGame.END_STATE] = new EndDrawEngineImpl();
        drawEngine = boardDrawEngines[BoardGame.NONE_STATE];
*/
    }

    public void updateState(int state) {
        AndroidLog.i(TAG, "STATE: " + state);
        switch (state) {
            default:
                break;
        }
        this.parent.update();
    }
    @Override
    public void onDraw(Canvas g, Bitmap[] blockImages, Bitmap[] buttonImages) {
        commonDrawEngine.onDraw(g, game, boardProfile, blockImages, buttonImages);
        drawEngine.onDraw(g, game, boardProfile, blockImages, buttonImages);
    }

}
