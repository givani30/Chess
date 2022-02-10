package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class King extends Piece implements Movable {

    private final Movable bishop;
    private Movable rook;
    private boolean canCastle;

    public King(ChessColor targetChessColor) {
        super(targetChessColor);
        this.bishop = new Bishop(targetChessColor);
        this.rook = new Rook(targetChessColor);
        setPieceType(PieceTypes.KING);
        canCastle = true;
    }

    public King(ChessColor targetChessColor, Movable bishop, Movable rook) {
        super(targetChessColor);
        this.bishop = bishop;
        this.rook = rook;
        setPieceType(PieceTypes.KING);
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
        Location currentLoc = this.getCurrentSpot().getLocation();
        return getValidMoves(board,currentLoc);
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc) {
        ArrayList<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(bishop.getValidMoves(board, currentLoc));
        moveCandidates.addAll(rook.getValidMoves(board, currentLoc));
        return moveCandidates.stream().filter(candidate -> {
            return ((Math.abs(currentLoc.getFile().ordinal() - candidate.getFile().ordinal()) <= 1)
                && (Math.abs(currentLoc.getRank() - candidate.getRank()) <= 1));
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
