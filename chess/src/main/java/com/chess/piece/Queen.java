package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece implements Movable {

    private final Movable bishop;
    private Movable rook;

    public Queen(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceType.QUEEN);
        this.bishop = new Bishop(targetChessColor);
        this.rook= new Rook(targetChessColor);
    }
    public Queen(ChessColor targetChessColor, Movable bishop, Movable rook){
        super(targetChessColor);
        setPieceType(PieceType.QUEEN);
        this.bishop=bishop;
        this.rook=rook;

    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // Check whether piece is alive
        if (!isAlive() ) {return false;}

        // TODO Move logic
        return true;
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board) {
        ArrayList<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(bishop.getValidMoves(board));
        moveCandidates.addAll(rook.getValidMoves(board));
        return moveCandidates;
    }


    @Override
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc) {
        ArrayList<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(bishop.getValidMoves(board, currentLoc));
        moveCandidates.addAll(rook.getValidMoves(board, currentLoc));
        return moveCandidates;
    }
}
