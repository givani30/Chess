package com.chess.common;

public enum ChessColor {
    WHITE, BLACK;
public ChessColor toggle(){
    if (this== WHITE) return BLACK;
    else return WHITE;
}
}
