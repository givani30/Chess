package com.chess;

import com.chess.pieces.Piece;
import com.chess.player.Player;

public class Move {
    private Game game;
    private Board board;
    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    private boolean castlingMove;
    public Move(Game game, Player player, Spot start, Spot end){

        this.game=game;
        board=game.getGameBoard();
        this.player=player;
        this.start=start;
        this.end=end;
        this.pieceMoved= start.getPiece();

        //Checks if it is the players turn
        if(!playerTurn(game,player)){
            System.err.println("It is not " + player.getPlayerColor().toString() +"'s turn");

            //Checks if the move is pseudo-legal
        }else if (!pieceMoved.canMove(board, start, end)){
            System.err.println("Piece cannot perform move!");
        }

        //Performs move
        else{
            end.getPiece().kill();
            end.clear();
            end.setPiece(start.getPiece());
            start.clear();

        }
    }

    /**
     * Small check to know whether it is the players turn
     * @param game The current game
     * @param player The current player
     * @return Whether it is the players turn or not
     */
    public static boolean playerTurn(Game game, Player player){
        return game.getPlayerTurn()==player.getPlayerColor();
    }
}
