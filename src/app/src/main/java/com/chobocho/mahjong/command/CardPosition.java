package com.chobocho.mahjong.command;

public class CardPosition {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public CardPosition(){

    }

    public CardPosition(int x1, int y1, int x2, int y2) {
        set(x1, y1, x2, y2);
    }

    public void set(int x1, int y1, int x2, int y2) {
        if (x1 > x2) {
            this.x1 = x2;
            this.x2 = x1;
        } else {
            this.x1 = x1;
            this.x2 = x2;
        }

        if (y1 > y2) {
            this.y1 = y2;
            this.y2 = y1;
        } else {
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    public boolean isInRange(int x, int y) {
        return (x > x1) && (x < x2) && (y > y1) && (y < y2);
    }

    public String toString() {
        return " (" + x1 + "," + y1 + "), (" + x2 +"," + y2 +")";
    }
}
