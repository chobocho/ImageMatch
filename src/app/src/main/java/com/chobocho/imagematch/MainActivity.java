package com.chobocho.imagematch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        gameView = new MahjongGameView(this, majhong, boardProfile, cmdEngine);
    }
}
