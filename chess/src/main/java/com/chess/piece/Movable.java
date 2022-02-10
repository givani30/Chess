package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;

import java.util.ArrayList;

/**
 * Interface for standardizing movement functions
 */
public interface Movable {

    /**
     * Function to evaluate what Moves are available for the Movable object
     * @param board The board that the object resides on
     * @return ArrayList of valid locations
     */
    public ArrayList<Location> getValidMoves(Board board);

    /**
     * Function to evaluate what Moves are available for the Movable object
     * @param board The board that the object resides on
     * @param currentLoc The current location of the object
     * @return ArrayList of valid locations
     */
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc);
}
