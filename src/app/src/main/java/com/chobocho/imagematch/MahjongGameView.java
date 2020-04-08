package com.chobocho.imagematch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chobocho.imagematch.cmd.CommandFactoryManager;
import com.chobocho.imagematch.ui.DrawModeManager;
import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.Mahjong;
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

    private HandlerThread gameHandlerThread;
    private Handler gameHandler;
    private static final int EMPTY_MESSAGE = 0;

    public MahjongGameView(Context context, BoardGame game, BoardProfile boardProfile, CommandEngine cmdEngine) {
        super(context);
        this.mContext = context;
        this.boardProfile = boardProfile;

        loadImage();

        this.game = game;
        this.cmdEngine = cmdEngine;

        drawEngine = new DrawModeManagerImpl(game, boardProfile, cmdEngine, this);
        commandFactory = new CommandFactoryManagerImpl(game, boardProfile, cmdEngine);
        createGameThread();
    }


    private void createGameThread() {
        Log.d(TAG,"createPlayerThread");
        gameHandlerThread = new HandlerThread("Game Processing Thread");
        gameHandlerThread.start();
        gameHandler = new Handler(gameHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg){
                if (game != null && game.isPlayState()) {
                    Log.i(TAG, "1 seconds");
                    int gameSpeed = 800; // - (player.getScore() / 10000);
                    if (gameHandler.hasMessages(EMPTY_MESSAGE)) {
                        gameHandler.removeMessages(EMPTY_MESSAGE);
                    }
                    if (!game.tick()) {
                        ((Mahjong)game).gameoverState();
                    } else {
                        gameHandler.sendEmptyMessageDelayed(EMPTY_MESSAGE, gameSpeed);
                    }
                    invalidate();
                }
            }
        };
    }


    public void startGame() {
        Log.d(TAG, "startGame");
        gameHandler.sendEmptyMessage(EMPTY_MESSAGE);
    }

    public void pauseGame() {
        Log.d(TAG, "pauseGame");
        if (gameHandler.hasMessages(EMPTY_MESSAGE)) {
            gameHandler.removeMessages(EMPTY_MESSAGE);
            gameHandler = null;
            Log.d(TAG, "Removed event");
        }
        if (game != null && game.isPlayState()) {
            game.pause();
        }

        if (gameHandlerThread != null) {
            gameHandlerThread.quit();
        }
        // saveScore();
    }

    public void resumeGame() {
        Log.d(TAG, "resumeGame");
        createGameThread();
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
