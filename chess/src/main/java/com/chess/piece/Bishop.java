package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationTools;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece implements Movable{


    public Bishop(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceTypes.BISHOP);
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
        Location currentLoc = this.getCurrentSpot().getLocation();
        return getValidMoves(board,currentLoc);
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc) {
        ArrayList<Location> moveCandidates = new ArrayList<>();
        Map<Location, Spot> locationSpotMap = board.getLocationSpotMap();
        getBishopMoves(moveCandidates,locationSpotMap,currentLoc,1,1);
        getBishopMoves(moveCandidates,locationSpotMap,currentLoc,-1,-1);
        getBishopMoves(moveCandidates,locationSpotMap,currentLoc,1,-1);
        getBishopMoves(moveCandidates,locationSpotMap,currentLoc,-1,1);
        return moveCandidates;
    }

    /**
     * Gets valid bishop moves in one offset direction
     * @param moveCandidates
     * @param locationSpotMap
     * @param currentLocation
     * @param fileOffset
     * @param rankOffset
     */
    private void getBishopMoves(List<Location> moveCandidates,
                                Map<Location, Spot> locationSpotMap,
                                Location currentLocation, int fileOffset, int rankOffset) {
        Location nextLoc = LocationTools.build(currentLocation, fileOffset, rankOffset);
        while (locationSpotMap.containsKey(nextLoc)) {
            Spot nextSpot = locationSpotMap.get(nextLoc);
            if (nextSpot.isOccupied()) {
                if (nextSpot.getPiece().getPieceColor().equals(this.getPieceColor())) {
                    break;
                }
                moveCandidates.add(nextLoc);
                break;
            }
            moveCandidates.add(nextLoc);
            nextLoc = LocationTools.build(nextLoc, fileOffset,rankOffset);
        }
    }
}
