package com.chobocho.imagematch;

public class BoardProfile {
    final static public String TAG = "BoardProfile";
    final static public int NEW_GAME_BUTTON = 0;
    final static public int ABOUT_BUTTON = 1;
    final static public int RANKING_BUTTON = 2;
    final static public int QUIT_BUTTON = 3;
    final static public int START_BUTTON = 4;
    final static public int PAUSE_BUTTON = 5;
    final static public int RESUME_BUTTON = 6;
    final static public int WIN_BUTTON = 7;
    final static public int TIMER_BAR = 8;


    public static int screenW = 1080;
    public static int screenH = 1820;

    public static int blockSize = 130;

    public static int startX = 0;
    public static int startY = blockSize;
    public static int endX = 1080;
    public static int endY = 1820;

    static public int buttonW = 400;
    static public int buttonH = 130;
    static public int buttonX =  (screenW - buttonW)/2;
    static public int buttonY =  50;

    final static public int boardWidth = 8;
    final static public int boardHeight = 12;

    public static int blockKind = 43;

    public BoardProfile(int w, int h) {
        setScreenSize(w, h);
    }

    public void setScreenSize(int w, int h) {
        this.screenW = w;
        this.screenH = h;

        int widthBlockSize = (int) (h / (boardHeight+3));
        blockSize = (int) (w / boardWidth);
        AndroidLog.i(TAG, "W: " + blockSize + ", H: " + widthBlockSize);
        blockSize = blockSize <= widthBlockSize ? blockSize : widthBlockSize;
        startX = (int)((w - (blockSize * boardWidth))/2);
        startY = blockSize;
        endX = startX + blockSize * boardWidth;
        endY = startY + blockSize * boardHeight;
        buttonW = blockSize * 4;
        buttonH = ((int)(blockSize * 1.5));

        buttonX =  (screenW - buttonW)/2;
        buttonY =  blockSize*2;
    }

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
            R.drawable.block35,
            R.drawable.block36,
            R.drawable.block37,
            R.drawable.block38,
            R.drawable.block39,
            R.drawable.block40,
            R.drawable.block41,
            R.drawable.block42
    };

    final static public int[] ButtonImageName = {
            R.drawable.newgame,
            R.drawable.about,
            R.drawable.ranking,
            R.drawable.quit,
            R.drawable.start,
            R.drawable.pause,
            R.drawable.resume,
            R.drawable.win,
            R.drawable.time
    };

}
