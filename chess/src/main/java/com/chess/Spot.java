package com.chess;

import com.chess.pieces.Piece;

public class Spot {
    private Piece piece;
    private int xLoc;
    private int yLoc;

    /**
     * Constructor for a spot on a chess board
     * @param xLoc The x location
     * @param yLoc The y location
     * @param piece The chess piece in the spot
     */
    public Spot(int xLoc, int yLoc, Piece piece) {
        setPiece(piece);
        setxLoc(xLoc);
        setyLoc(yLoc);
    }

    /**
     * Constructor to generate an empty spot
     * @param xLoc The x location
     * @param yLoc The y location
     */
    public Spot(int xLoc, int yLoc) {
        setxLoc(xLoc);
        setyLoc(yLoc);
    }

    /**
     *
     * @return the x Location
     */
    public int getXLoc() {
        return xLoc;
    }

    /**
     * @return the yLoc
     */
    public int getyLoc() {
        return yLoc;
    }

    /**
     * @param xLoc the xLoc to set
     */
    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    /**
     * @param yLoc the yLoc to set
     */
    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    /**
     * @param piece the piece to set
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * @return the piece in the spot
     */
    public Piece getPiece() {
        return piece;
    }

    /** Removes a piece from the spot */
    public void clear() {
        this.setPiece(null);
    }
}
