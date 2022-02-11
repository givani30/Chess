package com.chess.graphics;

import com.chess.board.Board;
import com.chess.common.ChessColor;
import com.chess.common.ChessFiles;
import com.chess.common.Location;
import com.chess.piece.Piece;
import com.chess.spot.Spot;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInterface extends JPanel {

    private static final ChessFiles[] files= ChessFiles.values();
    private static final String COLS = "ABCDEFGH";
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private static final int SQUARE_SIZE =64;
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private final Image[][] chessPieceImages = new Image[2][6];
    private final JLabel message = new JLabel(
        "BookStone Chess is ready to play!");
    private final Board board;
    private HashMap<Location,JButton> locationButtonMap;
    private boolean firstPress = false;
    private boolean secondPress = false;
    private Location selectedLoc1;
    private Location selectedLoc2;
    private ImageIcon invisibleIcon;

    public UserInterface(Board board) {
        createImages();
        this.board = board;
        invisibleIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        initializeGui(board);
       drawPieces();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.clearIcons();
        drawPieces();
        repaint();

    }

    private void initializeGui(Board board) {
        locationButtonMap = new HashMap<>();
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        Action newGameAction = new AbstractAction("New") {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearIcons();
                board.initBoard();
                drawPieces();
            }
        };

        tools.add(newGameAction);
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Load FEN")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);
        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        // the smaller of the two sizes
        JPanel chessBoard = new JPanel(new GridLayout(0, 9)) {
            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstraint (so it is centered).
             */
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                        (int) d.getWidth(), (int) d.getHeight());
                } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (Math.min(w, h));
                return new Dimension(s, s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
            new EmptyBorder(8,8,8,8),
            new LineBorder(Color.BLACK)
        ));
        // Set the BG color
        Color bgColor = new Color(160, 78, 1);
        chessBoard.setBackground(bgColor);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(bgColor);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);

        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                Location location = new Location(files[jj],ii+1);
                JButton b = new JButton(location.toString());
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon.
                b.setIcon(invisibleIcon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                    //) {
                    || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(new Color(255, 223, 177));
                } else {
                    b.setBackground(new Color(142, 86, 0));
                }

                locationButtonMap.put(location,b);
                b.addActionListener(e -> {
                    buttonPressed(location);
                });
            }
        }

        /*
         * fill the chess board
         */
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                new JLabel(COLS.substring(ii, ii + 1),
                    SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                if (jj == 0) {
                    chessBoard.add(new JLabel("" + (9 - (ii + 1)),
                        SwingConstants.CENTER));
                }
                JButton b = locationButtonMap.get(new Location(files[jj],7-ii+1));
                chessBoard.add(b);
            }
        }
    }

    public final JComponent getGui() {
        return gui;
    }

    public HashMap<Location, JButton> getLocationButtonMap() {
        return locationButtonMap;
    }

    /**
     * Grabs the chess piece image and splits it up in a 2d-array
     */
    private void createImages() {
        BufferedImage bufferedChessImage = null;
        try {
            bufferedChessImage = ImageIO.read(new File("chess/ChessPieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < chessPieceImages.length; i++) {
            for (int j = 0; j < chessPieceImages[i].length; j++){
                assert bufferedChessImage != null;
                chessPieceImages[i][j] =bufferedChessImage.getSubimage(j* SQUARE_SIZE,i* SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }

        }
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        this.addMouseListener(this);
//        this.addMouseMotionListener(this);
//        this.setBackground(Color.blue);
//
//        //Draw board
//        for (int y = 0; y < 8; y++) {
//            for (int x = 0; x < 8; x++) {
//                boolean isLightSquare = (x + y) % 2 != 0;
//                if (!isLightSquare) {
//                    g.setColor(new Color(252, 204, 116));
//                } else {
//                    g.setColor(new Color(138, 120, 93));
//                }
//                g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
//
//            }
//        }
//    }

    private void buttonPressed(Location l){

        if (!firstPress){
            System.out.println(l.toString()+ " 1st Press");
            //Enable first-press
            //Check if not empty
            if(board.getSpot(l).isEmpty()){
                return;
            }
            selectedLoc1 =l;
            //TODO: Show available squares
            firstPress = true;
        }else {
            System.out.println(l.toString()+ " 2nd Press");
            //Check if move valid
            selectedLoc2 =l;
            ArrayList<Location> possibleMoves = board.getSpot(selectedLoc1).getPiece().getValidMoves(board);
            //Check if move is valid, else return
            if(!possibleMoves.contains(selectedLoc2)){
                System.out.println("Invalid move");
                selectedLoc1 = null;
                selectedLoc2= null;
                firstPress = false;
                return;
            }
            if(!board.makeMove(selectedLoc1,selectedLoc2)){
                System.out.println("Move failed");
            }else {
                System.out.println("Move made from" + selectedLoc1.toString() + " to " + selectedLoc2.toString());
                getLocationButtonMap().get(selectedLoc2).setIcon(getLocationButtonMap().get(selectedLoc1).getIcon());
               // getLocationButtonMap().get(selectedLoc1).setIcon(invisibleIcon);
            }

           board.printBoard();
            selectedLoc1=null;
            selectedLoc2=null;
            firstPress = false;
            drawPieces();
            //clearIcons();
        }

    }


    //Draw pieces
    @Deprecated
    private void drawPiecesOld() {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                int yOffset=0;
                int xOffset=0;
                    Spot loc = board.getSpot(file,rank);
                    if(loc.isEmpty()) {
                        return;
                    }
                    if(loc.getPiece().getPieceColor().equals(ChessColor.BLACK)){
                        yOffset=1;
                    }

                    switch(loc.getPiece().getPieceType()){
                        case KING:
                            break;
                        case QUEEN:
                            xOffset = 1;
                            break;
                        case ROOK:
                            xOffset = 2;
                            break;
                        case BISHOP:
                            xOffset = 3;
                            break;
                        case KNIGHT:
                            xOffset = 4;
                            break;
                        case PAWN:
                            xOffset = 5;
                            break;
                    }
                    chessBoardSquares[file][7-rank].setIcon(new ImageIcon(chessPieceImages[yOffset][xOffset]));
                }

            }

        }

        private void clearIcon(JButton b){
        b.setIcon(invisibleIcon);
        }
        private void clearIcons(){
            HashMap<Location, JButton> buttonMap = this.getLocationButtonMap();
            for (JButton b:buttonMap.values()
                 ) {
                clearIcon(b);
            }
        }

        private void drawPieces(){
            HashMap<Location, JButton> buttonMap = this.getLocationButtonMap();
            HashMap<Piece, Location> pieceMap = board.getPieceLocationMap();
            this.clearIcons();
            for (Piece p: pieceMap.keySet()
                 ) {
                int yOffset=0;
                int xOffset=0;
                Location l = pieceMap.get(p);

                if(p.getPieceColor().equals(ChessColor.BLACK)){
                    yOffset=1;
                }

                switch(p.getPieceType()){
                    case KING:
                        break;
                    case QUEEN:
                        xOffset = 1;
                        break;
                    case ROOK:
                        xOffset = 2;
                        break;
                    case BISHOP:
                        xOffset = 3;
                        break;
                    case KNIGHT:
                        xOffset = 4;
                        break;
                    case PAWN:
                        xOffset = 5;
                        break;
                }
                buttonMap.get(l).setIcon(new ImageIcon(chessPieceImages[yOffset][xOffset]));
                gui.repaint();
            }
        }
}
