package com.chess;

import com.chess.board.Board;
import com.chess.common.ChessFiles;
import com.chess.common.Location;
import com.chess.graphics.ChessGUI;
import com.chess.graphics.UserInterface;

import javax.swing.*;
import java.util.Scanner;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Hello world!
 */
public final class MainChess {
    private static JFrame frame;

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        Board board = new Board();
     // board.initBoard();
    board.loadFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
//        board.loadFEN("r1b1k1nr/p2p1pNp/n2B4/1p1NP2P/6P1/3P1Q2/P1P1K3/q5b1 w");

        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        Runnable r = new Runnable() {
            @Override
            public void run() {
            drawBoard(board);
            }
        };
        SwingUtilities.invokeLater(r);
        while (true){
            // E2 -> E4
            //After clicking piece-- show available moves for piece

            String line = scanner.nextLine();
            if (!line.contains(" ")){
                continue;
            }
            String[] fromTo =line.split(" ");
            ChessFiles fromFile = ChessFiles.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
            int fromRank = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
            ChessFiles toFile = ChessFiles.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
            int toRank = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));

            Location fromLoc = new Location(fromFile,fromRank);
            Location toLoc = new Location(toFile,toRank);

            board.makeMove(fromLoc,toLoc);
            board.printBoard();
        }


//
//        Player p1 = new HumanPlayer(ChessColor.LIGHT);
//        Player p2 = new HumanPlayer(ChessColor.DARK);
//        Game game = new Game(p1,p2);


    }

    private static void drawBoard(Board board) {
//    frame.setUndecorated(true);
        UserInterface ui =new UserInterface(board);
        frame = new JFrame("BookStone Chess");
        frame.add(ui.getGui());
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public void redrawBoard(){
        this.getFrame().repaint();
    }
}
