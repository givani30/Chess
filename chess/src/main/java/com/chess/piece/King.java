package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

import java.util.ArrayList;

public class King extends Piece implements Movable {

    private boolean canCastle;

    public King(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceType.KING);
        canCastle = true;
    }


    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Check whether piece is alive
        if (!isAlive()) {
            return false;
        }
        // TODO move logic

        return true;
    }

    public boolean isCastlingDone() {
        return this.canCastle;
    }

    public void setCastling(boolean castlingDone) {
        this.canCastle = castlingDone;
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
