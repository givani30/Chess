package com.chess.pieces;

import com.chess.Board;
import com.chess.Spot;

public class King extends Piece {

    private boolean canCastle;

    public King(Color targetColor) {
        super(targetColor);
        setPieceType(PieceType.KING);
        canCastle = true;
    }


    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Check whether piece is alive
        if (!isAlive() ) {return false;}
        // TODO move logic

        return true;
    }

    public boolean isCastlingDone() {
        return this.canCastle;
    }

    public void setCastling(boolean castlingDone) {
        this.canCastle = castlingDone;
    }
}
