package com.chess.board;

import com.chess.Move;
import com.chess.common.ChessFiles;
import com.chess.common.Location;
import com.chess.piece.*;
import com.chess.common.ChessColor;
import com.chess.player.EnginePlayer;
import com.chess.player.HumanPlayer;
import com.chess.player.Player;
import com.chess.spot.Spot;

import java.util.*;

public class Board {

    private static final int BOARD_LENGTH = 8;
    private static final int BOARD_WIDTH = 8;
    private final Spot[][] playingBoard=new Spot[BOARD_LENGTH][BOARD_WIDTH];
    private final ArrayList<Move> moves;
    private ChessColor playerTurn;
    private final Player playerWhite;
    private final Player playerBlack;
    private final HashMap<Location,Spot> locationSpotMap;
    private final HashMap<Piece, Location> pieceLocationMap;

    public Board() {
        locationSpotMap=new HashMap<>();
        pieceLocationMap=new HashMap<>();
        this.createBoard();
        this.playerWhite = new HumanPlayer(ChessColor.WHITE);
        this.playerBlack = new HumanPlayer(ChessColor.BLACK);
        moves = new ArrayList<>();
    }


    public Spot getSpot(Location loc){
        return getLocationSpotMap().get(loc);
    }
    /**
     * Get the spot at location
     * @param rank The rank of the spot
     * @param fileInt The file of the spot
     * @return The Spot
     * @throws Exception If index is out of bounds
     */
    public Spot getSpot(int fileInt, int rank) {
        if (fileInt < 0 || fileInt > 7 || rank < 0 || rank > 7) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ChessFiles file = ChessFiles.values()[fileInt];
        Location location = new Location(file, rank+1);
        return getSpot(location);
    }

    /**
     * Clears the board
     */
    private void createBoard() {
        for (int rank = 0; rank < 8; rank++) { //Rank iterator
            int column=0;
            ChessColor currentColor = (rank%2!=0) ? ChessColor.WHITE :ChessColor.BLACK;
            for (ChessFiles file: ChessFiles.values()) { //File iterator
                Location l = new Location(file,rank+1);
                Spot newSpot = new Spot(l,currentColor);
                locationSpotMap.put(newSpot.getLocation(),newSpot);
                playingBoard[rank][column] = newSpot;
                currentColor = (currentColor ==ChessColor.BLACK) ? ChessColor.WHITE : ChessColor.BLACK;
                column++;
            }
        }
    }

    public boolean makeMove(Location startLoc, Location endLoc){

        Spot start = locationSpotMap.get(startLoc);
        Spot end = locationSpotMap.get(endLoc);
        return makeMove(start,end);
    }
    public boolean makeMove(Spot start, Spot end){

        //Checks if it is the players turn
        if(playerTurn!=start.getPiece().getPieceColor()){
            System.out.println("It is not " + playerTurn.toggle() +"'s turn");
        return false;
        }
            //Checks if the move is pseudo-legal
        Move move = new Move(this,getCurrentPlayer(),start,end);
        if(move.getPieceKilled()!=null){
            pieceLocationMap.remove(move.getPieceKilled());
        }

        //Updates Piecehashmap
        pieceLocationMap.replace(move.getPieceMoved(),end.getLocation());
        playerTurn=playerTurn.toggle();
        start.clear();
        moves.add(move);
        return true;
    }


    public void printBoard(){
        int i=8;
        for(Spot[] row : playingBoard){
            System.out.print((i)+" ");
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
            i--;
            System.out.println();
        }
        System.out.print("  ");
        for(ChessFiles file: ChessFiles.values()){
            System.out.print(file.name() + " ");
        }
        System.out.println();
    }


    /**
     * Clears boards
     */
    public void clearBoard() {
        moves.clear();
        for (Spot[] spots : playingBoard) {
            for (Spot spot : spots) {
                spot.clear();
            }
        }
        pieceLocationMap.clear();
    }

    /**
     * initializes board according to normal chess
     */
    public void initBoard() {
        loadFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
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
        playerTurn=(data[1].equals("w"))? ChessColor.WHITE : ChessColor.BLACK;

        String[] ranks = position.split("/");
        Collections.reverse(Arrays.asList(ranks));
       if (ranks.length!=8){
           System.out.println("String is not a valid FEN");
        }
        else{
            clearBoard();
           ChessFiles[] files = ChessFiles.values();
           int fileIterator = 0;
        for (String rank : ranks) {
            char[] chars = rank.toCharArray();
            int rankIndex = 0;
            for (char index : chars) {
                if (Character.isDigit(index)) {
                    //Skip values
                    rankIndex += Character.getNumericValue(index);
                } else {
                    playingBoard[fileIterator][rankIndex].setPiece(PieceFactory.pieceBuilder(index));
                    pieceLocationMap.put((playingBoard[fileIterator][rankIndex].getPiece()), new Location(files[rankIndex], fileIterator+1));
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
        ArrayList<Piece> lightPieces = new ArrayList<>();
        for (Piece p:getPieceLocationMap().keySet()
             ) {
            ChessColor color = p.getPieceColor();
            if(color==ChessColor.WHITE){
                lightPieces.add(p);
            }
        }
        return lightPieces;
    }

    public ArrayList<Piece> getDarkPieces() {
        ArrayList<Piece> darkPieces = new ArrayList<>();
        for (Piece p:getPieceLocationMap().keySet()
        ) {
            ChessColor color = p.getPieceColor();
            if(color==ChessColor.BLACK){
                darkPieces.add(p);
            }
        }
        return darkPieces;
    }

    public HashMap<Piece, Location> getPieceLocationMap() {
        return pieceLocationMap;
    }

    public ChessColor getPlayerTurn() {
        return playerTurn;
    }
    public Player getCurrentPlayer(){
        return playerTurn.equals(playerBlack.getPlayerColor())? playerBlack: playerWhite ;
    }
}
