package com.chess.board;

import com.chess.common.File;
import com.chess.common.Location;
import com.chess.piece.*;
import com.chess.common.ChessColor;
import com.chess.spot.Spot;

import java.util.*;

public class Board {

    private static final int BOARD_LENGTH = 8;
    private static final int BOARD_WIDTH = 8;
    private final Spot[][] playingBoard=new Spot[BOARD_LENGTH][BOARD_WIDTH];
    private ChessColor playerTurn;
    private final Map<Location,Spot> locationSpotMap;

    public Board() {
        locationSpotMap=new HashMap<>();
        this.createBoard();
    }

    /**
     * Get the spot at location
     * @param x The x location of the spot
     * @param y The y location of the spot
     * @return The Spot
     * @throws Exception If index is out of bounds
     */
    public Spot getSpot(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bounds");
        }
        return playingBoard[x][y];
    }

    /**
     * Clears the board
     */
    private void createBoard() {
        for (int rank = 0; rank < 8; rank++) { //Rank iterator
            int column=0;
            ChessColor currentColor = (rank%2!=0) ? ChessColor.LIGHT :ChessColor.DARK;
            for (File file: File.values()) { //File iterator
                Location l = new Location(file,rank+1);
                Spot newSpot = new Spot(l,currentColor);
                locationSpotMap.put(newSpot.getLocation(),newSpot);
                playingBoard[rank][column] = newSpot;
                currentColor = (currentColor ==ChessColor.DARK) ? ChessColor.LIGHT : ChessColor.DARK;
                column++;
            }
        }
    }

    public Map<Location, Spot> getLocationSpotMap() {
        return locationSpotMap;
    }

    public void printBoard(){
        for(Spot[] row : playingBoard){
            for (Spot spot:
                 row) {
                System.out.print(spot);
            }
            System.out.println();
        }
    }

    public Spot getSpot(Location loc){
        int rank = loc.getRank();
        File file = loc.getFile();

        return null;
    }

    /**
     * Clears boards
     */
    public void clearBoard() {
        for (int i = 0; i < playingBoard.length; i++) {
            for (int j = 0; j < playingBoard[i].length; j++) {
                playingBoard[i][j].clear();
            }
        }
    }

    /**
     * initializes board according to normal chess
     */
    public void initBoard() {

        // White pieces
        playingBoard[0][0].setPiece(new Rook(ChessColor.LIGHT));
        playingBoard[0][1].setPiece(new Knight(ChessColor.LIGHT));
        playingBoard[0][2].setPiece(new Bishop(ChessColor.LIGHT));
        playingBoard[0][3].setPiece(new Queen(ChessColor.LIGHT));
        playingBoard[0][4].setPiece(new King(ChessColor.LIGHT));
        playingBoard[0][5].setPiece(new Bishop(ChessColor.LIGHT));
        playingBoard[0][6].setPiece(new Knight(ChessColor.LIGHT));
        playingBoard[0][7].setPiece(new Rook(ChessColor.LIGHT));
        for (int i = 0; i < 7; i++) {
            playingBoard[1][i].setPiece(new Pawn(ChessColor.LIGHT));
        }

        // Black pieces
        playingBoard[7][0].setPiece(new Rook(ChessColor.DARK));
        playingBoard[7][1].setPiece(new Knight(ChessColor.DARK));
        playingBoard[7][2].setPiece(new Bishop(ChessColor.DARK));
        playingBoard[7][3].setPiece(new Queen(ChessColor.DARK));
        playingBoard[7][4].setPiece(new King(ChessColor.DARK));
        playingBoard[7][5].setPiece(new Bishop(ChessColor.DARK));
        playingBoard[7][6].setPiece(new Knight(ChessColor.DARK));
        playingBoard[7][7].setPiece(new Rook(ChessColor.DARK));
        for (int i = 0; i < 7; i++) {
            playingBoard[6][i].setPiece(new Pawn(ChessColor.DARK));

        }
        playerTurn = ChessColor.LIGHT;
    }

    /**
     * Loads board according to FEN notation
     * @param fenSTRING the FEN string for the board
     */
    public void loadFEN(String fenSTRING) {

        String[] data=fenSTRING.split(" ");
        String position = data[0];
        playerTurn=(Objects.equals(data[1], "w"))? ChessColor.LIGHT : ChessColor.DARK ;

        String[] ranks = position.split("/");
        Collections.reverse(Arrays.asList(ranks));
       if (ranks.length!=8){
           System.out.println("String is not a valid FEN");
        }
        else{
            clearBoard();
        for (String rank : ranks) {
            char[] chars = rank.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char index =chars[i];
                if( Character.isDigit(index)){
                    Character.getNumericValue(index);
                }

            }
        }
        }
        //TODO: Implement code
    }
}
