package com.chobocho.mahjong.board;

class Block {
    public int type;
    public int x;
    public int y;
    public int far;
    public boolean isHint;

    public Block () {
        this.init();
    }

    public void init()
    {
        type = 0;
        x = 0;
        y = 0;
        far = 0;
        isHint = false;
    }
    public void set(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void set(int type, int x, int y, int far) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.far = far;
    }
    public void set(Block b) {
        this.type = b.type;
        this.x = b.x;
        this.y = b.y;
        this.far = b.far;
    }
}
