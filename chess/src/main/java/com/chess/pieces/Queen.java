package com.chess.pieces;

import com.chess.Board;
import com.chess.Spot;

public class Queen extends Piece {


    public Queen(Color targetColor) {
        super(targetColor);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // Check whether piece is alive
        if (!isAlive() ) {return false;}

        // TODO Auto-generated method stub
        return false;
    }

}
