package com.chobocho.imagematch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.MahjongImpl;
import com.chobocho.mahjong.Score;
import com.chobocho.mahjong.command.CommandEngine;

public class MainActivity extends AppCompatActivity {
    BoardProfile boardProfile;
    Score score = new MajhongScore(this);
    BoardGame majhong = new MahjongImpl(new AndroidLog(), score, boardProfile.boardWidth, boardProfile.boardHeight, boardProfile.blockKind);
    CommandEngine cmdEngine = new CommandEngine(majhong);
    MahjongGameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(gameView);
    }

    protected void init() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        ((Display) display).getSize(size);
        int width = size.x;
        int height = size.y;
        boardProfile = new BoardProfile(width, height);
        boardProfile.setScreenSize(width, height);
        gameView = new MahjongGameView(this, majhong, boardProfile, cmdEngine);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (gameView != null) {
            gameView.pauseGame();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gameView != null) {
            gameView.resumeGame();
        }
    }
}
