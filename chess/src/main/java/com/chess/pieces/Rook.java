package com.chess.pieces;

import com.chess.Board;
import com.chess.Spot;

public class Rook extends Piece {

    public Rook(Color targetColor) {
        super(targetColor);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // Check whether piece is alive
        if (!isAlive() ) {return false;}
        int xMove = Math.abs(start.getXLoc() - end.getXLoc());
        int yMove = Math.abs(start.getyLoc() - end.getyLoc());

        return (xMove == 0 || yMove == 0);
    }

}
