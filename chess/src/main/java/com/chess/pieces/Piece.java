package com.chess.pieces;

import com.chess.Board;
import com.chess.Spot;

enum Pieces{
    PAWN,BISHOP,KING,QUEEN,ROOK,KNIGHT
    }

public abstract class Piece {

    private com.chess.pieces.Color pieceColor;
    private boolean isAlive;

    protected Piece(Color targetColor) {

        pieceColor = targetColor;
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
    public Color getPieceColor() {
        return pieceColor;
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

    /**
     * Checks whether the piece can move
     *
     * @param board The board where it can move
     * @param start The starting location
     * @param end   The ending location
     * @return true if it is a legal move
     */
    public abstract boolean canMove(Board board, Spot start, Spot end);
}
