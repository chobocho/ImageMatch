package com.chobocho.mahjong.command;

import com.chobocho.mahjong.BoardGame;
import com.chobocho.util.CLog;

import java.util.HashMap;

public class CommandEngine {
    final static String TAG = "CommandEngine";
    BoardGame game;
    HashMap<String, ComandFunction> functionMap;
    boolean isRunning;

    public CommandEngine(BoardGame game) {
        isRunning = false;
        this.game = game;
        this.functionMap = new HashMap<String, ComandFunction>();
        initFunction();
    }

    private void initFunction() {
        functionMap.put(PlayCommand.PLAY, new PlayFunction());
        functionMap.put(PlayCommand.PAUSE, new PauseFunction());
        functionMap.put(PlayCommand.IDLE, new IdleFunction());
        functionMap.put(PlayCommand.WIN, new WinFunction());
        functionMap.put(PlayCommand.REMOVE, new RemoveFunction());
    }

    public boolean runCommand (PlayCommand command) {
        if (command == null) {
            CLog.i(TAG, "Command is null");
            return false;
        }
        if (isRunning) {
            CLog.i(TAG, "Previous command is not finished");
            return false;
        }
        isRunning = true;

        CLog.i(TAG, command.toString());
        boolean result = functionMap.get(command.command).run(this.game, command.x1, command.y1, command.x2, command.y2);
        if (game.isFinishGame()) {
            isRunning = false;
            return functionMap.get(PlayCommand.WIN).run(this.game, command.x1, command.y1, command.x2, command.y2);
        }
        isRunning = false;
        return result;
    }
}
