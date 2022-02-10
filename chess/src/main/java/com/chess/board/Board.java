package com.chess.board;

import com.chess.Move;
import com.chess.common.File;
import com.chess.common.Location;
import com.chess.piece.*;
import com.chess.common.ChessColor;
import com.chess.player.HumanPlayer;
import com.chess.player.Player;
import com.chess.spot.Spot;

import java.util.*;

public class Board {

    private static final int BOARD_LENGTH = 8;
    private static final int BOARD_WIDTH = 8;
    private final Spot[][] playingBoard=new Spot[BOARD_LENGTH][BOARD_WIDTH];
    private final ArrayList<Piece> lightPieces = new ArrayList<>();
    private final ArrayList<Piece> darkPieces = new ArrayList<>();
    private ArrayList<Move> moves;
    private ChessColor playerTurn;
    private final Player playerWhite;
    private final Player playerBlack;
    private final Map<Location,Spot> locationSpotMap;
    private final Map<Piece, Location> pieceLocationMap;

    public Board() {
        locationSpotMap=new HashMap<>();
        pieceLocationMap=new HashMap<>();
        this.createBoard();
        this.playerWhite = new HumanPlayer(ChessColor.LIGHT);
        this.playerBlack = new HumanPlayer(ChessColor.DARK);
        moves = new ArrayList<>();
    }

    /**
     * Get the spot at location
     * @param x The x location of the spot
     * @param y The y location of the spot
     * @return The Spot
     * @throws Exception If index is out of bounds
     */
    public Spot getSpot(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IndexOutOfBoundsException("Index out of bounds");
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

    public void makeMove(Location startLoc, Location endLoc){
        Spot start = locationSpotMap.get(startLoc);
        Spot end = locationSpotMap.get(endLoc);
        Move move = new Move(this,getCurrentPlayer(),start,end);
        locationSpotMap.get(startLoc).clear();
    }
    public void makeMove(Spot start, Spot end){

        //Checks if it is the players turn
        if(playerTurn!=start.getPiece().getPieceColor()){
            System.out.println("It is " + playerTurn +"'s turn");}
            //Checks if the move is pseudo-legal
        Move move = new Move(this,getCurrentPlayer(),start,end);
        start.clear();
        moves.add(move);
    }


    public void printBoard(){
        int i=0;
        for(Spot[] row : playingBoard){
            System.out.print((i+1)+" ");
            for (Spot spot:
                 row) {
                if(spot.isOccupied()){
                    Piece piece = spot.getPiece();
                    char handle= piece.getPieceType().getChar(piece.getPieceColor());
                    System.out.print(handle+" ");
                } else {
                    //Empty square
                    System.out.print("- ");
                }
            }
            i++;
            System.out.println();
        }
        System.out.print("  ");
        for(File file: File.values()){
            System.out.print(file.name() + " ");
        }
        System.out.println();
    }


    /**
     * Clears boards
     */
    public void clearBoard() {
        moves.clear();
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
        clearBoard();
        // White pieces
        playingBoard[0][0].setPiece(new Rook(ChessColor.LIGHT));
        playingBoard[0][1].setPiece(new Knight(ChessColor.LIGHT));
        playingBoard[0][2].setPiece(new Bishop(ChessColor.LIGHT));
        playingBoard[0][3].setPiece(new Queen(ChessColor.LIGHT));
        playingBoard[0][4].setPiece(new King(ChessColor.LIGHT));
        playingBoard[0][5].setPiece(new Bishop(ChessColor.LIGHT));
        playingBoard[0][6].setPiece(new Knight(ChessColor.LIGHT));
        playingBoard[0][7].setPiece(new Rook(ChessColor.LIGHT));
        for (int i = 0; i < 8; i++) {
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
        for (int i = 0; i < 8; i++) {
            playingBoard[6][i].setPiece(new Pawn(ChessColor.DARK));

        }
        playerTurn = ChessColor.LIGHT;
    }

    public String getFEN(){
        //TODO
        return null;
    }
    /**
     * Loads board according to FEN notation
     * @param fenSTRING the FEN string for the board
     */
    public void loadFEN(String fenSTRING) {

        String[] data=fenSTRING.split(" ");
        String position = data[0];
        playerTurn=(data[1].equals("w"))? ChessColor.LIGHT : ChessColor.DARK ;

        String[] ranks = position.split("/");
        Collections.reverse(Arrays.asList(ranks));
       if (ranks.length!=8){
           System.out.println("String is not a valid FEN");
        }
        else{
            clearBoard();
           File[] files = File.values();
           int fileIterator = 0;
        for (String rank : ranks) {
            char[] chars = rank.toCharArray();
            int rankIndex = 0;
            for (int i = 0; i < chars.length; i++) {
                char index =chars[i];
                if( Character.isDigit(index)){
                    //Skip values
                    rankIndex += index;
                }else {
                    playingBoard[fileIterator][rankIndex].setPiece(PieceFactory.pieceBuilder(index));
                    rankIndex++;
                    //Place pieces
                }
            }
            fileIterator++;
        }
        }
        //TODO: Implement behaviour for castling/en passant
    }
    public Map<Location, Spot> getLocationSpotMap() {
        return locationSpotMap;
    }

    public ArrayList<Piece> getLightPieces() {
        return lightPieces;
    }

    public ArrayList<Piece> getDarkPieces() {
        return darkPieces;
    }

    public ChessColor getPlayerTurn() {
        return playerTurn;
    }
    public Player getCurrentPlayer(){
        return playerTurn.equals(playerBlack.getPlayerColor())? playerBlack: playerWhite ;
    }
}
