/**********************************************************************
 * Creates the properties for a king chess piece and determines its
 * valid movements.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public class King extends ChessPiece {

	/*****************************************************************
	 Constructor creates a king chess piece and assigns the owner
	 of the piece.
	 @param player the owner of this piece
	 *****************************************************************/
	public King(Player player) {
		super(player);
	}


	/*****************************************************************
	 Returns the king's title in a string format
	 @return "King"
	 *****************************************************************/
	public String type() {
		return "King";
	}


	/*****************************************************************
	 Determines if the move is valid for a king piece,
	 returning false if invalid, true if valid.
	 @param move the move to check
	 @param board the board the game is taking place on
	 @return true if valid, false if invalid
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;
		//calls the chesspiece isvalidmove method to check for generic validations
		if(!super.isValidMove(move, board)) {
			return false;
		}
		//Checks if the king moved one space in any direction
        if(Math.abs(move.fromRow-move.toRow) > 1 || Math.abs(move.fromColumn-move.toColumn) > 1){
			return false;
		}
			return valid;

	}
}
