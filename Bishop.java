/**********************************************************************
 * Creates the properties for a bishop chess piece and determines its
 * valid movements.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public class Bishop extends ChessPiece {

	/*****************************************************************
	 Constructor creates a bishop chess piece and assigns the owner
	 of the piece.
	 @param player the owner of this piece
	 *****************************************************************/
	public Bishop(Player player) {
		super(player);
	}


	/*****************************************************************
	 Returns the bishop's title in a string format
	 @return "Bishop"
	 *****************************************************************/
	public String type() {
		return "Bishop";
	}


	/*****************************************************************
	 Determines if the move is valid for a bishop piece,
	 returning false if invalid, true if valid.
	 @param move the move to check
	 @param board the board the game is taking place on
	 @return true if valid, false if invalid
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		//calls the chesspiece isvalidmove method to check for generic validations
		if(!super.isValidMove(move, board))
			return false;
		try {
			//Performs |x1-x2|/|y1-y2|, which should always be equal to one based on the movement of the rook, returns invalid if not
			if (Double.valueOf(Math.abs(move.fromRow - move.toRow)) / Double.valueOf(Math.abs(move.fromColumn - move.toColumn)) != 1) {
				return false;
			}
		}
		//if the denominator is 0, then the bishop attempted to move only vertically, so it is invalid
		catch(ArithmeticException e){
			return false;
		}

		//integer used to determine which way the rook is moving vertically
		int diff = move.toRow - move.fromRow;
		//integer used to determine which way the rook is moving horizontally
		int diffc = move.toColumn - move.fromColumn;

		//if statements for checking if the bishop will pass through a piece, returning invalid if it does
		//moving diagonally up and to the right
		if(diff < 0 && diffc > 0){
			for(int i = -1; i > diff; i--){
				if(board[move.fromRow+i][move.fromColumn-i] != null){
					return false;
				}
			}
		}
		//moving diagonally down and to the right
		if(diff > 0 && diffc > 0){
			for(int i = 1; i < diff; i++){
				if(board[move.fromRow+i][move.fromColumn+i] != null){
					return false;
				}
			}
		}
		//moving diagonally down and to the left
		if(diff > 0 && diffc < 0){
			for(int i = 1; i < diff; i++){
				if(board[move.fromRow+i][move.fromColumn-i] != null){
					return false;
				}
			}
		}
		//moving diagonally up and to the left
		if(diff < 0 && diffc < 0) {
			for(int i = -1; i > diff; i--) {
				if (board[move.fromRow + i][move.fromColumn + i] != null) {
					return false;
				}
			}
		}
		return true;

	}
}