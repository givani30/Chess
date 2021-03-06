package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationTools;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Pawn extends Piece implements Movable {

    private int moveDirection=1;
    private boolean isFirstMove=true;

    public Pawn(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceTypes.PAWN);
        if(this.getPieceColor()==ChessColor.BLACK){
            moveDirection = -1;
        }
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Check whether piece is alive
        if (!isAlive() ) {return false;}
        return true;
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board) {
        Location currentLoc = getCurrentSpot().getLocation();
       return getValidMoves(board, currentLoc);
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc) {

        ArrayList<Location> moveCandidates = new ArrayList<>();

        moveCandidates.add(LocationTools.build(currentLoc,0,1*moveDirection));
        if(isFirstMove){
            moveCandidates.add(LocationTools.build(currentLoc,0,2*moveDirection));
        }
        moveCandidates.add(LocationTools.build(currentLoc,-1,1*moveDirection));
        moveCandidates.add(LocationTools.build(currentLoc,1,1*moveDirection));


        Map<Location, Spot> locationSpotMap = board.getLocationSpotMap();
       ArrayList<Location> validMoves=moveCandidates.stream()
            .filter(locationSpotMap::containsKey)
            .collect(Collectors.toCollection(ArrayList::new));

        return validMoves.stream().filter(candidate -> {
            if (candidate.getFile().equals(currentLoc.getFile()) &&
                locationSpotMap.get(candidate).isOccupied()) {
                return false;
            }
            Piece destPiece = locationSpotMap.get(candidate).getPiece();
            if(destPiece == null){return true;}

            return !locationSpotMap.get(candidate).getPiece().getPieceColor().equals(this.getPieceColor());
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
