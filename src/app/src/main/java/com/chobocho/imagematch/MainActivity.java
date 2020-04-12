package com.chobocho.imagematch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.chobocho.mahjong.BoardGame;
import com.chobocho.mahjong.MahjongImpl;
import com.chobocho.mahjong.GameInfo;
import com.chobocho.mahjong.command.CommandEngine;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    BoardProfile boardProfile;
    GameInfo gameInfo;
    BoardGame majhong;
    CommandEngine cmdEngine;
    MahjongGameView gameView;
    String versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        versionName = getVersionName();
        init();
        setContentView(gameView);
    }

    protected void init() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        ((Display) display).getSize(size);
        int width = size.x;
        int height = size.y;
        boardProfile = new BoardProfile(versionName, width, height);
        boardProfile.setScreenSize(width, height);
        gameInfo = new MajhongGameInfo(this);
        gameInfo.setMaxTime(BoardGame.MAX_TIME);
        majhong = new MahjongImpl(new AndroidLog(), gameInfo, boardProfile.boardWidth, boardProfile.boardHeight, boardProfile.blockKind+1);
        cmdEngine = new CommandEngine(majhong);
        gameView = new MahjongGameView(this, majhong, gameInfo, boardProfile, cmdEngine);
    }

    private String getVersionName() {
        try {
            PackageInfo pkgInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pkgInfo.versionName;
            Log.i(TAG, "Version Name : "+ version);
            return version;
        } catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, e.toString());
        }
        return "";
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
