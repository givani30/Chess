package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationTools;
import com.chess.spot.Spot;
import com.chess.common.ChessColor;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Knight extends Piece implements Movable{


    public Knight(ChessColor targetChessColor) {
        super(targetChessColor);
        setPieceType(PieceTypes.KNIGHT);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
//
//        // Check whether piece is alive
//        if (!isAlive() ) {return false;}
//
//        int xMove = Math.abs(start.getXLoc() - end.getXLoc());
//        int yMove = Math.abs(start.getyLoc() - end.getyLoc());
//        return xMove * yMove == 2;
        return true;
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board) {
        Location currentLoc = this.getCurrentSpot().getLocation();
        return getValidMoves(board,currentLoc);
    }

    @Override
    public ArrayList<Location> getValidMoves(Board board, Location currentLoc) {

        Map<Location, Spot> locationSpotMap = board.getLocationSpotMap();
        ArrayList<Location> moveCandidates = new ArrayList<>();
        moveCandidates.add(LocationTools.build(currentLoc,1,2));
        moveCandidates.add(LocationTools.build(currentLoc,1,-2));
        moveCandidates.add(LocationTools.build(currentLoc,-1,2));
        moveCandidates.add(LocationTools.build(currentLoc,-1,-2));
        moveCandidates.add(LocationTools.build(currentLoc,2,1));
        moveCandidates.add(LocationTools.build(currentLoc,2,-1));
        moveCandidates.add(LocationTools.build(currentLoc,-2,1));
        moveCandidates.add(LocationTools.build(currentLoc,-2,-1));

        //Filters to move inside the board
        ArrayList<Location> validMoves=moveCandidates.stream()
            .filter(locationSpotMap::containsKey)
            .collect(Collectors.toCollection(ArrayList::new));
        //Filters out spots that don't contain same colored pieces
        return validMoves.stream().filter(candidates ->{
            if(!locationSpotMap.get(candidates).isOccupied()){
                return true;
            } else{
                return (!locationSpotMap.get(candidates).getPiece().getPieceColor().equals(this.getPieceColor()));
            }
            }).collect(Collectors.toCollection(ArrayList::new));
    }
}
