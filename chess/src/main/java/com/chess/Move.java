package com.chess;

import com.chess.board.Board;
import com.chess.piece.Piece;
import com.chess.player.Player;
import com.chess.spot.Spot;

public class Move {
    private final Board board;
    private final Player player;
    private final Spot start;
    private final Spot end;
    private final Piece pieceMoved;
    private Piece pieceKilled;
    private boolean castlingMove;

    public Move(Board board, Player player, Spot start, Spot end){

        this.board=board;
        this.player=player;
        this.start=start;
        this.end=end;
        this.pieceMoved= start.getPiece();
        this.pieceKilled=null;

    if (!pieceMoved.canMove(board, start, end)){
        System.out.println("Piece cannot perform move!");
    }
        //Performs move
        else{
            if(end.isOccupied()) {
                pieceKilled= end.getPiece();
                end.getPiece().kill();
            }
            end.clear();
            end.setPiece(start.getPiece());
            start.clear();
        }
    }


    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public Spot getStart() {
        return start;
    }

    public Spot getEnd() {
        return end;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public Piece getPieceKilled() {
        return pieceKilled;
    }

    public boolean isCastlingMove() {
        return castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    /**
     * Small check to know whether it is the players turn
     * @param board The current board
     * @param player The current player
     * @return Whether it is the players turn or not
     */
    public static boolean playerTurn(Board board, Player player){
        return board.getPlayerTurn()==player.getPlayerColor();
    }
}
