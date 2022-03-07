/**********************************************************************
 * Creates the properties for a knight chess piece and determines its
 * valid movements.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public class Knight extends ChessPiece {

	/*****************************************************************
	 Constructor creates a knight chess piece and assigns the owner
	 of the piece.
	 @param player the owner of this piece
	 *****************************************************************/
	public Knight(Player player) {
		super(player);
	}


	/*****************************************************************
	 Returns the knight's title in a string format
	 @return "Knight"
	 *****************************************************************/
	public String type() {
		return "Knight";
	}


	/*****************************************************************
	 Determines if the move is valid for a bishop piece,
	 returning false if invalid, true if valid.
	 @param move the move to check
	 @param board the board the game is taking place on
	 @return true if valid, false if invalid
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board){
		//calls the chesspiece isvalidmove method to check for generic validations
		if(!super.isValidMove(move, board))
			return false;
		boolean valid = true;

		//checks if the knight moved 2 rows up or down
		if(Math.abs(move.fromRow-move.toRow) == 2) {
			//if it did then check if it moved one space to right or left, invalid if not
			if (Math.abs(move.fromRow - move.toRow) != 2 || Math.abs(move.fromColumn - move.toColumn) != 1) {
				valid = false;
			}
		}
		//if the knight did not move 2 rows, then check if it moved 1 row and 2 columns, invalid if not
		else if(Math.abs(move.fromRow-move.toRow) != 1 || Math.abs(move.fromColumn-move.toColumn) != 2){
			valid = false;
		}
		return valid;
		
	}

}
