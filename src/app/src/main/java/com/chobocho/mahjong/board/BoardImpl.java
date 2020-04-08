package com.chobocho.mahjong.board;

import com.chobocho.util.CLog;

import java.util.LinkedList;

public class BoardImpl implements Board {
    final static String TAG = "BoardImpl";
    protected int width;
    protected int height;
    protected int[][] board;
    protected InitBoardMethod initMethod;
    protected int blockCount = 0;

    int blockKind = 35;
    int EMPTY = 0;

    public BoardImpl(int w, int h, int blockKind) {
        width = w;
        height = h;
        this.blockKind = blockKind;
        this.initMethod = new InitBoardMethodImpl();
        initVars();
        initBoard();
    }

    public BoardImpl(int w, int h, int blockKind, InitBoardMethod initMethod) {
        width = w;
        height = h;
        this.blockKind = blockKind;
        this.initMethod = initMethod;
        initVars();
        initBoard();
    }

    public void SetInitMethod(InitBoardMethod initMethod) {
        this.initMethod = initMethod;
    }

    private void initVars() {
        board = new int[height+1][width+1];
    }

    private void initBoard() {
        CLog.i(TAG, "W:" + width + " ,H:" + height);
        blockCount = initMethod.init(board, width, height);
    }

    public void setStage(int stage) {
        CLog.i(TAG, "Set Stage " + stage);
        initBoard();

        blockCount += (stage*2);

        int blockStart = (int) (Math.random() * 15 - stage);
        int blockTypeRange = (int) (Math.random() * 15) + 8 + stage;

        if (blockStart < 1) {
            blockStart = 1;
        }

        if ( blockTypeRange > blockKind) {
            blockTypeRange = blockKind;
        }

        int loopCount = 1000;
        while (stage > 0 && loopCount > 0) {
            if (insertBlock(blockStart, blockTypeRange)) {
                stage--;
            }
            loopCount--;
        }
    }

    private boolean insertBlock(int blockStart, int blockTypeRange) {
        CLog.i(TAG, "insertBlock " + blockStart);
        int MAX_LOOPCOUNT = 100;

        int blockType = ((int)(Math.random() * blockKind) % blockTypeRange + blockStart ) % blockKind;

        if ( blockType == EMPTY ) {
            blockType = EMPTY + 1;
        }

        int one_x = 0;
        int one_y = 0;
        int two_x = 0;
        int two_y = 0;

        int end_x = 0;
        int start_x = 0;
        int start_y = 0;
        int end_y = 0;
        int i, j;
        int loopCount = 0;


        while (loopCount < MAX_LOOPCOUNT) {
            do {
                one_x = (int) (Math.random() * (width-3));
                one_y = (int) (Math.random() * (height-3));
            } while (board[one_y][one_x] != EMPTY);

            do {
                two_x = (int) (Math.random() * (width - one_x)) + one_x;
                two_y = (int) (Math.random() * (height - one_y)) + one_y;
            } while (board[two_y][two_x] != EMPTY);

            CLog.i(TAG, "W:" + one_x + " ,H:" + one_y);
            CLog.i(TAG, "W:" + two_x + " ,H:" + two_y);

            if (!isClosedBlock(one_x, one_y, two_x, two_y)) {
                break;
            }

            loopCount++;
        }

        if (loopCount >= MAX_LOOPCOUNT) {
            CLog.i(TAG, "insertBlock error: MAXC COUNT LOOP");
            return false;
        }

        board[one_y][one_x] = blockType;
        board[two_y][two_x] = blockType;

        return true;
    }

    private boolean isClosedBlock(int one_x, int one_y, int two_x, int two_y) {
        return ((two_x - one_x == 1) && (two_y == one_y)) ||
                ((one_x - two_x == 1) && (two_y == one_y)) ||
                ((two_x == one_x ) && (two_y - one_y == 1)) ||
                ((two_x == one_x ) && (one_y - two_y == 1)) ||
                ((two_x == one_x) && (two_y == one_y));
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean removeBlock(int x, int y){
        CLog.i(TAG, "removeBlock " + x + ", " + y);

        LinkedList<Block> removeBlocks = getRemovableBlocks(x, y);

        if (removeBlocks.size() > 0) {
            CLog.i(TAG, "removeBlock BlockCount " +blockCount);
            blockCount -= removeBlocks.size();
            for (Block block : removeBlocks) {
                board[block.y][block.x] = EMPTY;
            }
            CLog.i(TAG, "removeBlock BlockCount " +blockCount);
            CLog.i(TAG, "removeBlock count " +removeBlocks.size());
            removeBlocks.clear();
            return true;
        }
        return false;
    }

    public boolean isClear() {
        return blockCount == 0;
    }

    public boolean needShuffle() {
        return !isClear() && !isRemovable();
    }

    public boolean shuffle() {
        CLog.i(TAG, "shuffle()");
        int currentCount = blockCount;
        int maxCount = 100;

         while((!isRemovable()) && (maxCount > 0)) {
            setStage(currentCount/2);
            maxCount--;
        }

        if (maxCount <= 0) {
            CLog.i(TAG, "rearrange() maxCount over!");
            return false;
        }

        return true;
    }

    public boolean isRemovable() {
        CLog.i(TAG, "isRemovable()");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                LinkedList<Block> blocks = getRemovableBlocks(j, i);
                if (blocks.size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private LinkedList<Block> getRemovableBlocks(int x, int y){
        // CLog.i(TAG, "isRemovable " + x + ", " + y);
        LinkedList<Block> removableBlocks = new LinkedList<>();

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return removableBlocks;
        }

        if (board[y][x] != EMPTY) {
            return removableBlocks;
        }

        int i = 0;
        int j = 0;

        int far = 0;

        Block north = new Block();
        Block west = new Block();
        Block south = new Block();
        Block east = new Block();

        for (i = x, far = 0; i < width; i++, far++) {
            if (board[y][i] != EMPTY) {
                east.set(board[y][i], i, y, far);
                CLog.i(TAG, "Find!! E");
                break;
            }
        }

        for (i = x, far = 0; i >= 0; i--, far++) {
            if (board[y][i] != EMPTY) {
                west.set(board[y][i], i, y, far);
                CLog.i(TAG, "Find!! W");
                break;
            }
        }

        for (j = y, far = 0; j >= 0; j--, far++) {
            if (board[j][x] != EMPTY) {
                north.set(board[j][x], x, j, far);
                break;
            }
        }

        for (j = y, far = 0; j < height; j++, far++) {
            if (board[j][x] != EMPTY) {
                south.set(board[j][x], x, j, far);
                break;
            }
        }

        if (north.type != EMPTY) {
            if (north.type == east.type) {
                removableBlocks.add(north);
                removableBlocks.add(east);
                east.type = EMPTY;
            } else if (north.type == south.type) {
                removableBlocks.add(north);
                removableBlocks.add(south);
                south.type = EMPTY;
            } else if (north.type == west.type) {
                removableBlocks.add(north);
                removableBlocks.add(west);
                west.type = EMPTY;
            }
        }

        if (east.type != EMPTY) {
            if (east.type == south.type) {
                removableBlocks.add(east);
                removableBlocks.add(south);
                south.type = EMPTY;
            } else if (east.type == west.type) {
                removableBlocks.add(east);
                removableBlocks.add(west);
                west.type = EMPTY;
            }
        }

        if (south.type != EMPTY) {
            if (south.type == west.type) {
                removableBlocks.add(south);
                removableBlocks.add(west);
                west.type = EMPTY;
            }
        }


        return removableBlocks;
    }
}
