package com.chobocho.imagematch.cmd.game;

import com.chobocho.imagematch.AndroidLog;
import com.chobocho.imagematch.BoardProfile;
import com.chobocho.mahjong.command.*;


public class WinEndCommandFactoryStateImpl extends EndCommandFactoryStateImpl implements CommandFactoryState {
    final static String TAG = "WinEndCommandFactoryStateImpl";

    @Override
    public PlayCommand createCommand(int event, int x, int y) {
        AndroidLog.i(TAG, "Event:" + Integer.toString(event));
        if (event == CommandFactory.KEYPRESS_EVENT) {
            switch (x) {
                case 79: // O
                case 83: // S
                    return new PlayCommand(PlayCommand.PLAY, 0, 0, 0, 0);
            }
        } else if (event == CommandFactory.MOUSE_CLICK_EVENT) {
            for (ButtonPosition btn : buttons) {
                if (btn.isInRange(x, y)) {
                    return new PlayCommand(btn.id, 0, 0, 0, 0);
                }
            }
        }
        return null;
    }

    @Override
    public PlayCommand createCommand(int fromDeck, int fromPos, int toDeck, int toPos) {
        AndroidLog.i(TAG, "GameEndState");
        return null;
    }

    @Override
    public void addButtons() {
        AndroidLog.i(TAG, "addButtons");
        int screenW = BoardProfile.screenW;
        int buttonW = BoardProfile.buttonW;
        int buttonH = BoardProfile.buttonH;
        int buttonY = BoardProfile.buttonY;
        buttons.push(new ButtonPosition(PlayCommand.IDLE, (screenW-buttonW)/2, buttonY, (screenW-buttonW)/2+buttonW,buttonY+buttonH));
        AndroidLog.i(TAG,buttons.toString());
    }
}