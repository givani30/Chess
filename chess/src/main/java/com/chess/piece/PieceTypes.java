package com.chess.piece;

import com.chess.common.ChessColor;

public enum PieceTypes {
    PAWN(80),
    BISHOP(66),
    KING(75),
    QUEEN(81),
    ROOK(82),
    KNIGHT(78),
    NO_PIECE(Character.getNumericValue('e'));

    private final int charVal;

    PieceTypes(int charVal) {
        this.charVal = charVal;
    }

    public int getCharVal() {
        return charVal;
    }

    public char getChar(ChessColor color) {
        char character = (char) charVal;
        if (color.equals(ChessColor.DARK)) {
            character = Character.toLowerCase(character);
        }
        return character;
    }
}
