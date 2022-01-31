package com.chess.board;

import com.chess.common.File;
import com.chess.common.Location;
import com.chess.piece.*;
import com.chess.common.ChessColor;
import com.chess.spot.Spot;

public class Board {

    private static final int BOARD_LENGTH = 8;
    private static final int BOARD_WIDTH = 8;
    private Spot[][] playingBoard=new Spot[BOARD_LENGTH][BOARD_WIDTH];
    private ChessColor playerTurn;

    public Board() {
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
            ChessColor currentColor = (rank%2==0) ? ChessColor.LIGHT :ChessColor.DARK;
            for (File file: File.values()) { //File iterator
                Location l = new Location(file,BOARD_LENGTH-rank);
                Spot newSpot = new Spot(l,currentColor);
                playingBoard[rank][column] = newSpot;
                currentColor = (currentColor ==ChessColor.DARK) ? ChessColor.LIGHT : ChessColor.DARK;
                column++;
            }
        }
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
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
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
        playingBoard[1][0].setPiece(new Bishop(ChessColor.LIGHT));
        playingBoard[2][0].setPiece(new Knight(ChessColor.LIGHT));
        playingBoard[3][0].setPiece(new Queen(ChessColor.LIGHT));
        playingBoard[4][0].setPiece(new King(ChessColor.LIGHT));
        playingBoard[5][0].setPiece(new Knight(ChessColor.LIGHT));
        playingBoard[6][0].setPiece(new Bishop(ChessColor.LIGHT));
        playingBoard[7][0].setPiece(new Rook(ChessColor.LIGHT));
        for (int i = 0; i < 7; i++) {
            playingBoard[i][1].setPiece(new Pawn(ChessColor.LIGHT));
        }

        // Black pieces
        playingBoard[0][7].setPiece(new Rook(ChessColor.DARK));
        playingBoard[1][7].setPiece(new Bishop(ChessColor.DARK));
        playingBoard[2][7].setPiece(new Knight(ChessColor.DARK));
        playingBoard[3][7].setPiece(new Queen(ChessColor.DARK));
        playingBoard[4][7].setPiece(new King(ChessColor.DARK));
        playingBoard[5][7].setPiece(new Knight(ChessColor.DARK));
        playingBoard[6][7].setPiece(new Bishop(ChessColor.DARK));
        playingBoard[7][7].setPiece(new Rook(ChessColor.DARK));
        for (int i = 0; i < 7; i++) {
            playingBoard[i][6].setPiece(new Pawn(ChessColor.DARK));

        }
        playerTurn = ChessColor.LIGHT;
    }

    /**
     * Loads board according to FEN notation
     * @param fenSTRING the FEN string for the board
     */
    public void loadFEN(String fenSTRING) {
        //TODO: Implement code
    }
}
