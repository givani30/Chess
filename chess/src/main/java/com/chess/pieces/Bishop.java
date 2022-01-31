package com.chess.pieces;

import com.chess.Board;
import com.chess.Spot;

public class Bishop extends Piece {


    public Bishop(Color targetColor) {
        super(targetColor);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // Check whether piece is alive or the same type of piece is there
        // Check whether piece is alive
        if (!isAlive() ) {return false;}

        int xMove = Math.abs(start.getXLoc() - end.getXLoc());
        int yMove = Math.abs(start.getyLoc() - end.getyLoc());
        return (xMove == yMove);
    }

}
