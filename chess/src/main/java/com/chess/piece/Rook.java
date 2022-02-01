package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.ChessColor;
import com.chess.common.Location;
import com.chess.common.LocationTools;
import com.chess.spot.Spot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rook extends Piece implements Movable {

    public Rook(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceType.ROOK);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // Check whether piece is alive
//        if (!isAlive() ) {return false;}
//        int xMove = Math.abs(start.
//        int yMove = Math.abs(start.getyLoc() - end.getyLoc());
//
//        return (xMove == 0 || yMove == 0);
        return true;
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board) {
        return null;
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc) {
        ArrayList<Location> moveCandidates = new ArrayList<>();
        Map<Location, Spot> locationSpotMap = board.getLocationSpotMap();
        getRankCandidates(moveCandidates,locationSpotMap,currentLoc,1);
        getRankCandidates(moveCandidates,locationSpotMap,currentLoc,-1);
        getFileCandidates(moveCandidates,locationSpotMap,currentLoc,1);
        getFileCandidates(moveCandidates,locationSpotMap,currentLoc,-1);
    }


    private void getRankCandidates(ArrayList<Location> moveCandidates,
                                   Map<Location, Spot> locationSpotMap,
                                   Location currentLocation, int offset) {
        Location nextLoc = LocationTools.build(currentLocation, 0, offset);
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
            nextLoc = LocationTools.build(nextLoc, 0, offset);
        }
    }

    private void getFileCandidates(List<Location> moveCandidates,
                                   Map<Location, Spot> locationSpotMap,
                                   Location currentLocation, int offset) {
        Location nextLoc = LocationTools.build(currentLocation, offset, 0);
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
            nextLoc = LocationTools.build(nextLoc, offset, 0);
        }
    }
}
