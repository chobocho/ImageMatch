package com.chobocho.imagematch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.chobocho.imagematch.cmd.CommandFactoryManager;
import com.chobocho.imagematch.ui.DrawEngineManager;
import com.chobocho.imagematch.ui.DrawModeManager;
import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.command.CommandEngine;
import com.chobocho.mahjong.command.CommandFactory;
import com.chobocho.mahjong.command.PlayCommand;

import static android.content.Context.MODE_PRIVATE;

public class MahjongGameView extends View {
    private String TAG = this.getClass().getName();
    private Context mContext;

    BoardProfile boardProfile;

    BoardGame game;
    CommandEngine cmdEngine;
    DrawModeManager drawEngine;
    CommandFactoryManager commandFactory;

    Bitmap[] blockImages;
    Bitmap[] buttonImages;

    public MahjongGameView(Context context, BoardGame game, BoardProfile boardProfile, CommandEngine cmdEngine) {
        super(context);
        this.mContext = context;
        this.boardProfile = boardProfile;

        loadImage();

        this.game = game;
        this.cmdEngine = cmdEngine;

        drawEngine = new DrawModeManagerImpl(game, boardProfile, cmdEngine, this);
        commandFactory = new CommandFactoryManagerImpl(game, boardProfile, cmdEngine);
    }


    public void startGame() {

    }

    public void pauseGame() {

    }

    public void resumeGame() {

    }

    public void update() {
        Log.d(TAG, "View.update()");
        invalidate();
    }


    public void onDraw(Canvas canvas) {
        if (game == null) {
            return;
        }
        drawEngine.onDraw(canvas, blockImages, buttonImages);
    }

    private void loadImage() {
        blockImages = new Bitmap[boardProfile.imageName.length+1];
        for (int i = 0; i < boardProfile.imageName.length; i++) {
            blockImages[i] = BitmapFactory.decodeResource(mContext.getResources(), boardProfile.imageName[i]);
        }

        buttonImages = new Bitmap[boardProfile.ButtonImageName.length+1];
        for (int i = 0; i < boardProfile.ButtonImageName.length; i++) {
            buttonImages[i] = BitmapFactory.decodeResource(mContext.getResources(), boardProfile.ButtonImageName[i]);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (game == null) {
            return false;
        }

        int x = (int) (event.getX());
        int y = (int) (event.getY());

        if (MotionEvent.ACTION_UP == event.getAction()) {
            onTouchReleased(x, y);
        }

        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            onTouchPressed(x, y);
        }
        return true;
    }

    private void onTouchPressed(int mouseX, int mouseY) {
        Log.i(TAG, "Mouse Pressed " + mouseX + ":" + mouseY);
    }

    public void onTouchReleased(int mouseX, int mouseY) {
        Log.i(TAG, "Mouse released " + mouseX + ":" + mouseY);

        PlayCommand cmd = commandFactory.createCommand(CommandFactory.MOUSE_CLICK_EVENT, mouseX, mouseY);
        if (cmdEngine.runCommand(cmd)) {
            update();
        }
        update();
    }

}
