package com.chess.piece;

import com.chess.board.Board;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

public abstract class Piece implements Movable {

    private final ChessColor pieceChessColor;
    private boolean isAlive;
    private PieceTypes pieceTypes;
    private Spot currentSpot;

    protected Piece(ChessColor targetChessColor) {
        pieceChessColor = targetChessColor;
        this.isAlive = true;

    }

    /**
     *
     * @return Whether the piece is alive
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * @return true if the piece is black
     */
    public ChessColor getPieceColor() {
        return pieceChessColor;
    }

    /**
     * kills the Piece
     */
    public void kill() {
        boolean kill = true;
        kill(kill);
    }

    /**
     * Kills the piece, input false to revive it
     *
     * @param kill
     */
    public void kill(boolean kill) {
        isAlive = !kill;
    }

    public PieceTypes getPieceType() {
        return pieceTypes;
    }

    public void setPieceType(PieceTypes pieceTypes) {
        this.pieceTypes = pieceTypes;
    }

    public Spot getCurrentSpot() {
        return currentSpot;
    }

    public void setCurrentSpot(Spot currentSpot) {
        this.currentSpot = currentSpot;
    }

    /**
     * Checks whether the piece can move
     *
     * @param board The board where it can move
     * @param start The starting location
     * @param end   The ending location
     * @return true if it is a legal move
     */
    public abstract boolean canMove(Board board, Spot start, Spot end);

    @Override
    public String toString() {
        return pieceChessColor +
            "-" + pieceTypes +
            '}';
    }

}

