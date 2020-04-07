package com.chobocho.imagematch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.MahjongImpl;
import com.chobocho.mahjong.command.CommandEngine;

public class MainActivity extends AppCompatActivity {
    BoardProfile boardProfile;
    BoardGame majhong = new MahjongImpl(new AndroidLog(),boardProfile.boardWidth, boardProfile.boardHeight);
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
}
