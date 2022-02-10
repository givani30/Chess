package com.chess;
import com.chess.board.Board;
import com.chess.common.ChessColor;
import com.chess.common.File;
import com.chess.common.Location;
import com.chess.graphics.UserInterface;
import com.chess.player.HumanPlayer;
import com.chess.player.Player;
import com.chess.spot.Spot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;

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

        Board board = new Board();
     // board.initBoard();
     board.loadFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        drawBoard(board);
//        while (true){
//            // E2 -> E4
//            //After clicking piece-- show available moves for piece
//
//            String line = scanner.nextLine();
//            String[] fromTo =line.split("->");
//            File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
//            int fromRank = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
//            File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
//            int toRank = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));
//
//            Location fromLoc = new Location(fromFile,fromRank);
//            Location toLoc = new Location(toFile,toRank);
//
//            board.makeMove(fromLoc,toLoc);
//            board.printBoard();
//
//        }


//
//        Player p1 = new HumanPlayer(ChessColor.LIGHT);
//        Player p2 = new HumanPlayer(ChessColor.DARK);
//        Game game = new Game(p1,p2);


    }

    private static void drawBoard(Board board) {
        JFrame frame = new JFrame("BookStone Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10,10,512+15,512+40);
        frame.setVisible(true);
//    frame.setUndecorated(true);
        UserInterface ui =new UserInterface(board);
        frame.add(ui);

    }
}
