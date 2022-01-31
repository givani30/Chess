package com.chess;

import com.chess.pieces.Color;
import com.chess.player.Player;

public class Game {
    private Board gameBoard;
    private Player playerWhite;
    private Player playerBlack;
    private Color playerTurn;

    public Game(Player player1, Player player2){
        this.gameBoard = new Board();
        gameBoard.initBoard();
        this.playerWhite = player1;
        this.playerBlack = player2;
        setPlayerTurn(Color.WHITE);
    }

    /**
     * Gets the game board
     * @return the game board
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Sets the game board
     * @param gameBoard
     */
    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Gets the black player
     * @return Player black
     */
    public Player getPlayerBlack() {
        return playerBlack;
    }

    /**
     * Gets the white player
     * @return Player white
     */
    public Player getPlayerWhite() {
        return playerWhite;
    }

    public Color getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Color playerTurn) {
        this.playerTurn = playerTurn;
    }
}
