/**********************************************************************
 * Creates the generic properties for a chess piece.
 *
 * @author Brandon Rodriguez, Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public abstract class ChessPiece implements IChessPiece {

	/** The player which owns the chess piece */
	private Player owner;


	/*****************************************************************
	 Constructor creates a piece with entered player as the owner
	 @param player the owner of the piece
	 *****************************************************************/
	protected ChessPiece(Player player) {
		this.owner = player;
	}


	/*****************************************************************
	 Abstract method which will return the title of the piece
	 in a string format
	 *****************************************************************/
	public abstract String type();


	/*****************************************************************
	 Returns the owner of the piece
	 @return the owner of the piece
	 *****************************************************************/
	public Player player() {
		return owner;
	}

	/*****************************************************************
	 Determines if the move is a valid move using the generic
	 conditions that apply to all types of chess pieces.
	 @param move the move to check
	 @param board the board the game is taking place on
	 @return true if valid, false if invalid
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;
		//checks if the piece moved anywhere, returns invalid if not
		if (move.fromRow == move.toRow && move.fromColumn == move.toColumn)
			return false;
		//checks if the move is within the boundaries of the board
		if (move.toRow < 0 || move.toRow > 7 || move.toColumn < 0 || move.toColumn  > 7 || move.fromRow < 0 ||
				move.fromRow > 7 || move.fromColumn < 0 || move.fromColumn > 7 )
			return false;

		//checks if the piece is moving to a place occupied by a piece of the same color
		if (board[move.toRow][move.toColumn] != null){
			if(board[move.toRow][move.toColumn].player() == this.owner){
				return false;
			}
		}
		//verifies that "this" piece is located at the "from" location
		if(this != board[move.fromRow][move.fromColumn] && board[move.fromRow][move.fromColumn].type() != "Queen"){
			return false;
		}
		//checks if the move is within the boundaries of the board
		if(move.fromRow < 0 || move.fromRow > 7 || move.fromColumn < 0 || move.fromColumn > 7){
			return false;
		}

		return valid;
	}
}
