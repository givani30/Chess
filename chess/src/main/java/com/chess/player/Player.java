package com.chess.player;

import com.chess.common.ChessColor;

public abstract class Player {
    private final ChessColor playerChessColor;
    private boolean isHuman;

    protected Player(ChessColor chessColor){
        playerChessColor = chessColor;
    }
    public ChessColor getPlayerColor() {
        return playerChessColor;
    }
}
