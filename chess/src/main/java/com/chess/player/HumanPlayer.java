package com.chess.player;

import com.chess.common.ChessColor;

public class HumanPlayer extends Player{

    private final boolean isHuman;

    public HumanPlayer(ChessColor chessColor) {
        super(chessColor);
        isHuman=true;
    }
}
