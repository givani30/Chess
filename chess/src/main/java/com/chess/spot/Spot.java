package com.chess.spot;

import com.chess.common.ChessColor;
import com.chess.common.Location;
import com.chess.piece.Piece;

public class Spot {
    private Piece piece;
    private final Location location;
    private final ChessColor color;

    /**
     * Constructor for a spot on a chess board
     * @param piece The chess piece in the spot
     * @param location the location
     * @param color
     */
    public Spot(Piece piece, Location location, ChessColor color) {
        this.piece = piece;
        this.location = location;
        this.color = color;
    }

    /**
     * Generates an empty spot
     * @param location
     * @param color
     */
    public Spot(Location location, ChessColor color) {
        this.location=location;
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public ChessColor getColor() {
        return color;
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

    public boolean isEmpty(){
        return piece==null;
    }

    /** Removes a piece from the spot */
    public void clear() {
        this.setPiece(null);
    }

    @Override
    public String toString() {
        return "Spot{" +
            ", location=" + location +
            "piece=" + piece +
            ", color=" + color +
            '}';
    }
}
