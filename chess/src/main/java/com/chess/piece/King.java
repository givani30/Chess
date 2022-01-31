package com.chess.piece;

import com.chess.board.Board;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

public class King extends Piece {

    private boolean canCastle;

    public King(ChessColor targetChessColor) {
        super(targetChessColor);
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