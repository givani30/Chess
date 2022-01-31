package com.chess;

/**
 * Hello world!
 */
public final class MainChess {

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    void CreateGraphicalBoard(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                boolean isLightSquare = (x+y)%2 !=0;

            }
        }
    }
}
