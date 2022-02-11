package com.chess.piece;

import com.chess.common.ChessColor;
import com.chess.common.Location;

import java.util.HashMap;
import java.util.Map;

public final class PieceFactory {
    private PieceFactory(){}

    public static Map<Location, Piece> getPieces(){
        Map<Location, Piece> pieces = new HashMap<>();

        return null;
        //Rooks
    }
    public static Piece pieceBuilder(char pieceString){
        ChessColor pieceColor;
        Piece newPiece;
        if(Character.isUpperCase(pieceString)){pieceColor = ChessColor.WHITE;}
        else pieceColor =ChessColor.BLACK;

        switch(Character.toLowerCase(pieceString)){
            case 'k':
                newPiece = new King(pieceColor);
                break;
            case 'q':
                newPiece = new Queen(pieceColor);
                break;
            case 'r':
                newPiece=new Rook(pieceColor);
                break;

            case 'n':
                newPiece=new Knight(pieceColor);
                break;
            case 'b':
                newPiece = new Bishop(pieceColor);
                break;
            case 'p':
                newPiece = new Pawn(pieceColor);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Character.toLowerCase(pieceString));
        }
        return newPiece;
    }

}
