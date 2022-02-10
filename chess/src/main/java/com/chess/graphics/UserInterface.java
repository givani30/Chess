package com.chess.graphics;

import com.chess.board.Board;
import com.chess.common.ChessColor;
import com.chess.spot.Spot;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class UserInterface extends JPanel implements MouseListener, MouseMotionListener {

    static int x=0,y=0;
    static int squareSize=64;
    Image chessPieceImage;
    Board board;

    public UserInterface(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setBackground(Color.blue);

//        g.setColor(Color.blue);
//        g.fillRect(x-20,y-20,40,40);



        //Draw board
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                boolean isLightSquare = (x+y)%2 !=0;
                if(!isLightSquare){
                    g.setColor(new Color(252, 204, 116));
                }else{
                    g.setColor(new Color(138, 120, 93));
                }
                g.fillRect(x*squareSize,y*squareSize,squareSize,squareSize);

            }
        }

        chessPieceImage = new ImageIcon("chess/ChessPieces.png").getImage();

        /*
Draw pieces
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int yOffset=0;
                int xOffset=0;
                try {
                    Spot loc = board.getSpot(x, y);
                    if(!loc.isOccupied()) {
                        return;
                    }
                    if(loc.getPiece().getPieceColor().equals(ChessColor.DARK)){
                        yOffset=64;
                    }
                    switch(loc.getPiece().getPieceType()){
                        case KING:
                            xOffset = 0;
                            break;
                        case QUEEN:
                            xOffset = 64;
                            break;
                        case ROOK:
                            xOffset = 64*2;
                            break;
                        case BISHOP:
                            xOffset = 64*3;
                            break;
                        case KNIGHT:
                            xOffset = 64*4;
                            break;
                        case PAWN:
                            xOffset = 64*5;
                            break;
                    }
                    int xLoc = x*squareSize;
                    int yLoc = (7-y)*squareSize;
                    g.drawImage(chessPieceImage,xLoc,yLoc,xLoc+squareSize,yLoc+squareSize,xOffset,yOffset,xOffset+squareSize,yOffset+squareSize,this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        y=e.getY();
        x=e.getX();
        repaint();
    }
}
