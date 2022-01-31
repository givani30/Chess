package com.chess.piece;

import com.chess.board.Board;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

public class Pawn extends Piece {


    public Pawn(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceType.PAWN);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Check whether piece is alive
        if (!isAlive() ) {return false;}
        // TODO Move logic
        return true;
    }

}
