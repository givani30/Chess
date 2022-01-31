package com.chess.player;

import com.chess.pieces.Color;

public abstract class Player {
    private final Color playerColor;
    private boolean isHuman;

    protected Player(Color color){
        playerColor=color;
    }
    public Color getPlayerColor() {
        return playerColor;
    }
}
