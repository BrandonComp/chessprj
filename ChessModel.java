/**********************************************************************
 * Creates and manages the chess game.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

import java.util.*;

public class ChessModel implements IChessModel {
	/** the board where the game takes place */
    private IChessPiece[][] board;

	/** the player equal to black or white */
	private Player player;

	/** the list which contains the history of moves */
	private Move[] undoList;

	/** contains the history of pieces moved "from" */
	private IChessPiece[] fromList;

	/** contains the history of pieces moved "to" */
	private IChessPiece[] toList;


	/*****************************************************************
	 Default constructor creates a board with dimensions 8x8 and
	 adds the pieces to the board, also instantiates the lists for the
	 undo method.
	 *****************************************************************/
	public ChessModel() {
		board = new IChessPiece[8][8];
		player = Player.WHITE;
		undoList = new Move[1];
		fromList = new IChessPiece[1];
		toList = new IChessPiece[1];
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);
		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight (Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn(Player.WHITE);
		}
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Player.BLACK);
		}
	}

	/*****************************************************************
	 Determines if the current player is in checkmate or not.
	 @return checkmate returns true if in checkmate, false if not.
	 *****************************************************************/
	public boolean isComplete() {
		boolean checkmate = true;
		Player p = currentPlayer();
		//loops through the board
			for (int i = 0; i < 8; i++) {
				for (int i2 = 0; i2 < 8; i2++) {
					//if the piece at this space belongs to the current player
					if (board[i][i2] != null && board[i][i2].player() == p) {
						//loops through the board again
						for (int i3 = 0; i3 < 8; i3++) {
							for (int i4 = 0; i4 < 8; i4++) {
								//create a new move from the pieces space to the current space
								Move move = new Move(i, i2, i3, i4);
								if (board[i][i2] != null && board[i][i2].isValidMove(move, board)) {
									//attempt the current move
									var temp = board[i3][i4];
									board[i3][i4] = board[i][i2];
									board[i][i2] = null;
									//if the player is no longer in check after the move then they are not in checkmate
									if (!inCheck(p)) {
										checkmate = false;
									}
									//move the pieces back to their original position
									board[i][i2] = board[i3][i4];
									board[i3][i4] = temp;
								}
							}
						}
					}
				}
			}
		return checkmate;
	}


	/*****************************************************************
	 Determines if the move is valid for a generic piece
	 @return vaild returns true if valid, false if not.
	 *****************************************************************/
	public boolean isValidMove(Move move) {
		boolean valid = false;
		if (board[move.fromRow][move.fromColumn] != null) {
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board)) {
				return true;
			}
		}
		return valid;
	}

	/*****************************************************************
	 Adjusts the board based on the move entered as a parameter
	 @param move the move to perform
	 *****************************************************************/
	public void move(Move move) {
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	/*****************************************************************
	 Determines if the current player is attempting to put themselves
	 into check or checkmate.
	 @return true if true, false if false
	 *****************************************************************/
	public boolean selfCheck(Move move){
		boolean check = false;
		//perform the entered move
		var temp = board[move.toRow][move.toColumn];
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
		//if the current player is in check after their move then set check to true
		if (inCheck(currentPlayer())) {
			check = true;
		}
		//undo the move which was performed
		board[move.fromRow][move.fromColumn] = board[move.toRow][move.toColumn];
		board[move.toRow][move.toColumn] = temp;
		return check;
	}

	/*****************************************************************
	 Determines if the player entered as a parameter is in check
	 or not.
	 @return valid true if in check, false if not.
	 *****************************************************************/
	public boolean inCheck(Player p) {
		int x=0;
		int y=0;
		boolean check = false;
		//locate this player's king
		for (int i = 0; i <  8; i++) {
			for(int i2 = 0; i2 < 8; i2++) {
				if (board[i][i2] != null && board[i][i2].type() == "King" && board[i][i2].player() == p) {
					x = i2;
					y = i;
				}
			}
		}
		//loops through the board
		for(int i = 0; i < 8; i++){
			for(int i2 = 0; i2 < 8 ; i2++){
				Move move = new Move(i,i2,y,x);
				//if an opponents piece can move to the kings space set check to true
				if(board[i][i2] != null && board[i][i2].player() != p && board[i][i2].isValidMove(move,board)){
					check = true;
				}
			}
		}
		return check;
	}

	/*****************************************************************
	 Adds the entered move to the history of moves.
	 @return none
	 *****************************************************************/
	public void addUndo(Move m){
		Move[] newUndo = new Move[undoList.length+1];
		var from = board[m.fromRow][m.fromColumn];
		var to = board[m.toRow][m.toColumn];
		//adds the move to the history of moves
		System.arraycopy(undoList, 0, newUndo, 0, undoList.length);
		newUndo[undoList.length] = m;
		undoList = newUndo;

		//adds the "from" piece to the history of pieces
		IChessPiece[] newFromList = new IChessPiece[fromList.length+1];
		System.arraycopy(fromList, 0, newFromList, 0, fromList.length);
		newFromList[fromList.length] = from;
		fromList = newFromList;

		//adds the "to" piece to the history of pieces
		IChessPiece[] newToList = new IChessPiece[toList.length+1];
		System.arraycopy(toList, 0 , newToList, 0, toList.length);
		newToList[toList.length] = to;
		toList = newToList;
	}

	/*****************************************************************
	 Undoes the most recent move and removes it from the history of moves.
	 @return none
	 *****************************************************************/
	public void undo(){
		//do nothing if there are no moves to undo
		if(undoList.length == 1){
			return;
		}
		//pull the most recent move and pieces from the history arrays
		Move m = undoList[undoList.length-1];
		var from = fromList[fromList.length-1];
		var to = toList[toList.length-1];
		//re-add them to the board and switch the player
		board[m.fromRow][m.fromColumn] = from;
		board[m.toRow][m.toColumn] = to;
		setNextPlayer();
		//remove the move that was undone from the history arrays
		fromList = Arrays.copyOf(fromList,fromList.length-1);
		toList = Arrays.copyOf(toList,toList.length-1);
		undoList = Arrays.copyOf(undoList,undoList.length-1);
	}
	/*****************************************************************
	 Returns the current player
	 @return player
	 *****************************************************************/
	public Player currentPlayer() {
		return player;
	}

	/*****************************************************************
	 Returns the number of rows
	 @return 8
	 *****************************************************************/
	public int numRows() {
		return 8;
	}
	/*****************************************************************
	 Returns the number of columns
	 @return 8
	 *****************************************************************/
	public int numColumns() {
		return 8;
	}

	/*****************************************************************
	 Returns the piece from the entered row and column
	 @return board[row][col] the piece to return
	 *****************************************************************/
	public IChessPiece pieceAt(int row, int column) {		
		return board[row][column];
	}

	/*****************************************************************
	 Adds the entered move to the history of moves.
	 @return none
	 *****************************************************************/
	public void setNextPlayer() {
		player = player.next();
	}

	/*****************************************************************
	 Sets the entered space on the board to the entered piece.
	 @return none
	 *****************************************************************/
	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
	}

	/*****************************************************************
	 returns the length of the history of moves
	 @return undoList.length
	 *****************************************************************/
	public int getUndo(){
		return undoList.length;
	}

	/*****************************************************************
	 Determines if the black chess piece at the entered space is in danger
	 of being taken by a white chess piece. Used in the AI method.
	 @return true if in danger, false if not
	 *****************************************************************/
	public boolean pieceInDanger(int j, int j2){
		//for loops which go through all pieces on the board
		for(int i = 0; i < 8; i++){
			for(int i2 = 0; i2 < 8 ; i2++){
				Move move = new Move(i,i2,j,j2);
				//if a white piece can move to the entered piece's position then return true
				if(board[i][i2] != null && board[i][i2].player() == Player.WHITE && board[i][i2].isValidMove(move,board)){
					return true;
				}
			}
		}
		//if not return false
		return false;
	}

	/*****************************************************************
	 Attempts to determine the best move for the black player then
	 does that move. Not currently implemented as it causes other
	 methods to work incorrectly.
	 @return none
	 *****************************************************************/
	public void AI() {
		Player p = Player.BLACK;
		Player b = Player.WHITE;
		if(currentPlayer() == Player.BLACK){
			//if black is the current player then check to see if black is in check, and make a valid move out of check.
			if(inCheck(Player.BLACK)){
				boolean check = true;
				for (int i = 0; i < 8; i++) {
					for (int i2 = 0; i2 < 8; i2++) {
						if (board[i][i2] != null && board[i][i2].player() == p && check) {
							for (int i3 = 0; i3 < 8; i3++) {
								for (int i4 = 0; i4 < 8; i4++) {
									Move move = new Move(i, i2, i3, i4);
									if (board[i][i2] != null && board[i][i2].isValidMove(move, board) && check) {
										var temp = board[i3][i4];
										board[i3][i4] = board[i][i2];
										board[i][i2] = null;
										if (!inCheck(p)) {
											board[i][i2] = board[i3][i4];
											board[i3][i4] = temp;
											check = false;
											addUndo(move);
											board[i3][i4] = board[i][i2];
											board[i][i2] = null;
											setNextPlayer();
										}
										else {
											board[i][i2] = board[i3][i4];
											board[i3][i4] = temp;
										}
									}
								}
							}
						}
					}
				}
			}
			//if black is not in check, then look to see if we can put the opponent in check
			if(currentPlayer() == Player.BLACK) {
				boolean bcheck = false;
				for (int i = 0; i < 8; i++) {
					for (int i2 = 0; i2 < 8; i2++) {
						if (board[i][i2] != null && board[i][i2].player() == p && !bcheck) {
							for (int i3 = 0; i3 < 8; i3++) {
								for (int i4 = 0; i4 < 8; i4++) {
									Move move = new Move(i, i2, i3, i4);
									if (board[i][i2] != null && board[i][i2].isValidMove(move, board)) {
										var temp = board[i3][i4];
										board[i3][i4] = board[i][i2];
										board[i][i2] = null;
										//perform the move and check if the opponent is in check
										if (inCheck(b)) {
											board[i][i2] = board[i3][i4];
											board[i3][i4] = temp;
											bcheck = true;
											addUndo(move);
											board[i3][i4] = board[i][i2];
											board[i][i2] = null;
											setNextPlayer();
										} else {
											board[i][i2] = board[i3][i4];
											board[i3][i4] = temp;
										}
									}
								}
							}
						}
					}
				}
			}
			//check if any black pieces are in danger and attempt to move them out of danger if true.
			if(currentPlayer() == Player.BLACK){
				boolean done = false;
				for (int i = 0; i < 8; i++) {
					for (int i2 = 0; i2 < 8; i2++) {
						if (board[i][i2] != null && board[i][i2].player() == p && pieceInDanger(i,i2) && !done) {
							for (int i3 = 0; i3 < 8; i3++) {
								for (int i4 = 0; i4 < 8; i4++) {
									Move move = new Move(i, i2, i3, i4);
									if (board[i][i2] != null && board[i][i2].isValidMove(move, board) && pieceInDanger(i,i2) && !done) {
										var temp = board[i3][i4];
										board[i3][i4] = board[i][i2];
										board[i][i2] = null;
										if (!pieceInDanger(i3,i4)) {
											board[i][i2] = board[i3][i4];
											board[i3][i4] = temp;
											addUndo(move);
											board[i3][i4] = board[i][i2];
											board[i][i2] = null;
											done = true;
											setNextPlayer();
										} else {
											board[i][i2] = board[i3][i4];
											board[i3][i4] = temp;
										}
									}
								}
							}
						}
					}
				}
			}
			//if none of the above cases were true, then make a random move
			if(currentPlayer() == Player.BLACK) {
				boolean done = false;
				for (int i = 0; i < 8; i++) {
					for (int i2 = 0; i2 < 8; i2++) {
						if (board[i][i2] != null && board[i][i2].player() == p && !done) {
							for (int i3 = 0; i3 < 8; i3++) {
								for (int i4 = 0; i4 < 8; i4++) {
									Move move = new Move(i, i2, i3, i4);
									if (board[i][i2] != null && board[i][i2].isValidMove(move, board) && !done) {
										addUndo(move);
										var temp = board[i3][i4];
										board[i3][i4] = board[i][i2];
										board[i][i2] = null;
										setNextPlayer();
										done = true;
										}
									}
								}
							}
						}
					}
				}
		}
	}
}
