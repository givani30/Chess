package com.chess.pieces;

import com.chess.Board;
import com.chess.Spot;

public class Knight extends Piece {


    public Knight(Color targetColor) {
        super(targetColor);
        setPieceType(PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // Check whether piece is alive
        if (!isAlive() ) {return false;}

        int xMove = Math.abs(start.getXLoc() - end.getXLoc());
        int yMove = Math.abs(start.getyLoc() - end.getyLoc());
        return xMove * yMove == 2;
    }

}
