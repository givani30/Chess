package com.chess.piece;

import com.chess.board.Board;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

public class Knight extends Piece {


    public Knight(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
//
//        // Check whether piece is alive
//        if (!isAlive() ) {return false;}
//
//        int xMove = Math.abs(start.getXLoc() - end.getXLoc());
//        int yMove = Math.abs(start.getyLoc() - end.getyLoc());
//        return xMove * yMove == 2;
        return true;
    }

}
