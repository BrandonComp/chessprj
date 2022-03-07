/**********************************************************************
 * Creates the properties for a queen chess piece and determines its
 * valid movements.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public class Queen extends ChessPiece {

	/*****************************************************************
	 Constructor creates a queen chess piece and assigns the owner
	 of the piece.
	 @param player the owner of this piece
	 *****************************************************************/
	public Queen(Player player) { super(player); }


	/*****************************************************************
	 Returns the queen's title in a string format
	 @return "Queen"
	 *****************************************************************/
	public String type() { return "Queen"; }


	/*****************************************************************
	 Determines if the move is valid for a queen piece,
	 returning false if invalid, true if valid.
	 @param move the move to check
	 @param board the board the game is taking place on
	 @return true if valid, false if invalid
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		//calls the chesspiece isvalidmove method to check for generic validations
		if(!super.isValidMove(move, board))
			return false;

		//creates a bishop and a rook, whose movements combined equal the queen's movement
		Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
		Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());
		//invokes the bishop and rook isvalidmove methods to determine if the queen's move is valid
		return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
	}
}
