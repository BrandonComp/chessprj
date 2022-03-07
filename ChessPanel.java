/**********************************************************************
 * Manages the graphic interface for the chess game.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {

    /** board of JButtons */
    private JButton[][] board;
    /** the undo button */
    private JButton undoButton;
    /** the chess game model */
    private ChessModel model;

    /** images of the pieces */
    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    /** indicates the first turn */
    private boolean firstTurnFlag;

    /** boolean used to prevent multiple messages appearing */
    private boolean done;

    /** values for the movements */
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;

    /** listener for the gui */
    private listener listener;

    /*****************************************************************
     Default constructor which sets up the board for the start of the
     game.
     *****************************************************************/
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    placeWhitePieces(r, c);
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    placeBlackPieces(r, c);
                }

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        undoButton = new JButton("Undo");
        add(undoButton);
        firstTurnFlag = true;
        undoButton.addActionListener(new listener());
    }

    /*****************************************************************
     Sets the colors of the board to look like a typical chess board
     @return none
     *****************************************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
            board[r][c].setOpaque(true);
            board[r][c].setBorderPainted(false);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
            board[r][c].setOpaque(true);
            board[r][c].setBorderPainted(false);
        }
    }

    /*****************************************************************
     Places the image of the white piece on the board based on the
     chess model.
     @return none
     *****************************************************************/
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /*****************************************************************
     Places the image of the black piece on the board based on the
     chess model.
     @return none
     *****************************************************************/
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }


    /*****************************************************************
     Assigns the images of the pieces to their respective variables.
     @return none
     *****************************************************************/
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/wRook.png");
        wBishop = new ImageIcon("./src/wBishop.png");
        wQueen = new ImageIcon("./src/wQueen.png");
        wKing = new ImageIcon("./src/wKing.png");
        wPawn = new ImageIcon("./src/wPawn.png");
        wKnight = new ImageIcon("./src/wKnight.png");
        //Sets the Image for black player pieces
        bRook = new ImageIcon("./src/bRook.png");
        bBishop = new ImageIcon("./src/bBishop.png");
        bQueen = new ImageIcon("./src/bQueen.png");
        bKing = new ImageIcon("./src/bKing.png");
        bPawn = new ImageIcon("./src/bPawn.png");
        bKnight = new ImageIcon("./src/bKnight.png");
    }

    /*****************************************************************
     Updates the display of the board in the graphic interface.
     @return none
     *****************************************************************/
    private void displayBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);

                }
            }

        }
        repaint();
    }

    /*****************************************************************
     Inner class which handles the action listeners for the buttons,
     performing different actions depending on which one is selected.
     *****************************************************************/
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(undoButton == event.getSource()){
                //if there are no moves in the "undo list" then display a message and exit the method
                if(model.getUndo() == 1){
                    JOptionPane.showMessageDialog(null, "There are no moves to undo.");
                    return;
                }
                model.undo();
                displayBoard();
            }
            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())
                        if (firstTurnFlag) {
                            fromRow = r;
                            fromCol = c;
                            firstTurnFlag = false;
                        } else {
                            done = false;
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);
                            //checks if the move is valid and that the current player is equal to the selected pieces owner
                            if ((model.isValidMove(m)) && model.currentPlayer() == model.pieceAt(fromRow, fromCol).player()) {
                                //displays a message if the player is putting themselves into check
                                if(model.selfCheck(m)){
                                    JOptionPane.showMessageDialog(null, "You cannot put yourself" +
                                            " into check/checkmate");
                                    displayBoard();
                                    return;
                                }
                                model.addUndo(m);
                                model.move(m);
                                displayBoard();
                                //checks if the current player is in checkmate and displays a message if true
                                if(model.isComplete() && model.currentPlayer() == Player.BLACK){
                                    JOptionPane.showMessageDialog(null, "Checkmate, white wins");
                                    done = true;
                                }
                                if(model.isComplete() && model.currentPlayer() == Player.WHITE){
                                    JOptionPane.showMessageDialog(null, "Checkmate, black wins");
                                    done = true;
                                }
                                model.setNextPlayer();
                                //checks if the other player is in checkmate and displays a message if true
                                if(model.isComplete() && model.currentPlayer() == Player.BLACK && !done){
                                    JOptionPane.showMessageDialog(null, "Checkmate, white wins");
                                    done = true;
                                }
                                if(model.isComplete() && model.currentPlayer() == Player.WHITE && !done){
                                    JOptionPane.showMessageDialog(null, "Checkmate, black wins");
                                    done = true;
                                }
                                //checks if either player is in check and displays a message
                                if (model.inCheck(Player.WHITE) && !done) {
                                    JOptionPane.showMessageDialog(null, "White is in check");
                                }
                                if (model.inCheck(Player.BLACK) && !done) {
                                    JOptionPane.showMessageDialog(null, "Black is in check");
                                }
                            }
                            else {
                                //displays a message if the current player doesn't own the selected piece
                                if (model.pieceAt(fromRow, fromCol) != null && model.pieceAt(fromRow, fromCol).player() != model.currentPlayer()) {
                                    JOptionPane.showMessageDialog(null, "It is your opponents turn");

                                }
                                //if not then the move was invalid
                                else {
                                    JOptionPane.showMessageDialog(null, "Invalid move");
                                }
                            }
                        }
        }
    }
}
