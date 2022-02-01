package com.chess;
import com.chess.board.Board;
import com.chess.common.ChessColor;
import com.chess.common.File;
import com.chess.player.HumanPlayer;
import com.chess.player.Player;
import com.chess.spot.Spot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;

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
        Board board = new Board();
        board.initBoard();
        board.printBoard();
        board.loadFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");


        drawBoard();
        Player p1 = new HumanPlayer(ChessColor.LIGHT);
        Player p2 = new HumanPlayer(ChessColor.DARK);
        Game game = new Game(p1,p2);


    }

    private static void drawBoard() {
        JFrame frame = new JFrame();
        frame.setBounds(10,10,512,512);
//    frame.setUndecorated(true);
        JPanel pn =new  JPanel(){
            @Override
            public void paint(Graphics g){
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        boolean isLightSquare = (x+y)%2 !=0;
                        if(!isLightSquare){
                            g.setColor(Color.white.darker());
                        }else{
                            g.setColor(Color.black.brighter());
                        }
                        g.fillRect(x*64,y*64,64,64);
                    }
                }
            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
