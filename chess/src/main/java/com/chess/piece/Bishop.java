package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

import java.util.ArrayList;

public class Bishop extends Piece implements Movable{


    public Bishop(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
//
//        // Check whether piece is alive or the same type of piece is there
//        // Check whether piece is alive
//        if (!isAlive() ) {return false;}
//
//        int xMove = Math.abs(start.getXLoc() - end.getXLoc());
//        int yMove = Math.abs(start.getyLoc() - end.getyLoc());
//        return (xMove == yMove);
        //TODO
        return true;
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board) {
        //TODO
        return null;
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc) {
        //TODO
        return null;
    }
}
