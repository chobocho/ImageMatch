package com.chobocho.imagematch;

public class BoardProfile {
    final static public int NEW_GAME_BUTTON = 0;
    final static public int START_BUTTON = 4;
    final static public int RESUME_BUTTON = 5;
    final static public int WIN_BUTTON = 6;

    final static public int screenW = 1080;
    final static public int screenH = 1820;

    final static public int blockSize = 130;

    final static public int startX = 0;
    final static public int startY = blockSize;

    final static public int buttonW = 200;
    final static public int buttonH = 100;
    final static public int buttonX =  (screenW - buttonW)/2;
    final static public int buttonY =  50;

    final static public int boardWidth = 8;
    final static public int boardHeight = 12;



    public final static int[] imageName = {
            R.drawable.block0,
            R.drawable.block1,
            R.drawable.block2,
            R.drawable.block3,
            R.drawable.block4,
            R.drawable.block5,
            R.drawable.block6,
            R.drawable.block7,
            R.drawable.block8,
            R.drawable.block9,
            R.drawable.block10,
            R.drawable.block11,
            R.drawable.block12,
            R.drawable.block13,
            R.drawable.block14,
            R.drawable.block15,
            R.drawable.block16,
            R.drawable.block17,
            R.drawable.block18,
            R.drawable.block19,
            R.drawable.block20,
            R.drawable.block21,
            R.drawable.block22,
            R.drawable.block23,
            R.drawable.block24,
            R.drawable.block25,
            R.drawable.block26,
            R.drawable.block27,
            R.drawable.block28,
            R.drawable.block29,
            R.drawable.block30,
            R.drawable.block31,
            R.drawable.block32,
            R.drawable.block33,
            R.drawable.block34,
            R.drawable.block35
    };

    final static public String[] ButtonImageName = {
            "img/newgame.png",
            "img/about.png",
            "img/ranking.png",
            "img/quit.png",
            "img/start.png",
            "img/resume.png",
            "img/win.png"
    };

}
