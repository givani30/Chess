package com.chess;

import com.chess.pieces.*;

public class Board {

    private static final int LENGTH = 8;
    private static final int WIDTH = 8;
    private Spot[][] playingBoard;

    public Board() {
        this.createBoard();
        this.initBoard();

    }

    public Spot getSpot(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bounds");
        }
        return playingBoard[x][y];
    }

    // Empties the whole board
    public void createBoard() {

        playingBoard = new Spot[LENGTH][WIDTH];

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                playingBoard[i][j] = new Spot(i, j);

            }
        }
    }

    public void clearBoard() {

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                playingBoard[i][j].clear();
            }
        }

    }

    /** initializes board according to normal chess */
    public void initBoard() {

        // White pieces
        playingBoard[0][0].setPiece(new Rook(Color.WHITE));
        playingBoard[1][0].setPiece(new Bishop(Color.WHITE));
        playingBoard[2][0].setPiece(new Knight(Color.WHITE));
        playingBoard[3][0].setPiece(new Queen(Color.WHITE));
        playingBoard[4][0].setPiece(new King(Color.WHITE));
        playingBoard[5][0].setPiece(new Knight(Color.WHITE));
        playingBoard[6][0].setPiece(new Bishop(Color.WHITE));
        playingBoard[7][0].setPiece(new Rook(Color.WHITE));
        for (int i = 0; i < 7; i++) {
            playingBoard[i][1].setPiece(new Pawn(Color.WHITE));
        }

        // Black pieces
        playingBoard[0][7].setPiece(new Rook(Color.BLACK));
        playingBoard[1][7].setPiece(new Bishop(Color.BLACK));
        playingBoard[2][7].setPiece(new Knight(Color.BLACK));
        playingBoard[3][7].setPiece(new Queen(Color.BLACK));
        playingBoard[4][7].setPiece(new King(Color.BLACK));
        playingBoard[5][7].setPiece(new Knight(Color.BLACK));
        playingBoard[6][7].setPiece(new Bishop(Color.BLACK));
        playingBoard[7][7].setPiece(new Rook(Color.BLACK));
        for (int i = 0; i < 7; i++) {
            playingBoard[i][6].setPiece(new Pawn(Color.BLACK));
        }
    }
}
