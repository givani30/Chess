package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;

import java.util.ArrayList;
import java.util.List;

public interface Movable {

    public ArrayList<Location> getValidMoves(Board board);
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc);
}
