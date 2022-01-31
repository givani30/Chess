package com.chess.piece;

import com.chess.board.Board;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

public class Rook extends Piece {

    public Rook(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceType.ROOK);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // Check whether piece is alive
//        if (!isAlive() ) {return false;}
//        int xMove = Math.abs(start.
//        int yMove = Math.abs(start.getyLoc() - end.getyLoc());
//
//        return (xMove == 0 || yMove == 0);
        return true;
    }

}
