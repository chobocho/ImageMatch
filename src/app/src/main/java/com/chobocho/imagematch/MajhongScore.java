package com.chobocho.imagematch;

import android.content.Context;

import com.chobocho.mahjong.ScoreImpl;
import com.chobocho.mahjong.Score;

public class MajhongScore extends ScoreImpl implements Score {
    Context mContext;

    public MajhongScore(Context context) {
        mContext = context;
    }

    @Override
    public int calculatorScore(int removedBlockCount, int time) {
        int updateScore =  stage * removedBlockCount * removedBlockCount + (time * time);
        return updateScore > 100 ? updateScore / 10 : updateScore;
    }
}
