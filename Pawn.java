/**********************************************************************
 * Creates the properties for a pawn chess piece and determines its
 * valid movements.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public class Pawn extends ChessPiece {

	/*****************************************************************
	 Constructor creates a pawn chess piece and assigns the owner
	 of the piece.
	 @param player the owner of this piece
	 *****************************************************************/
	public Pawn(Player player) {
		super(player);
	}


	/*****************************************************************
	 Returns the pawn's title in a string format
	 @return "Pawn"
	 *****************************************************************/
	public String type() {
		return "Pawn";
	}


	/*****************************************************************
	 Determines if the move is valid for a pawn piece,
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
		//checks if this is the pawns first movement, allowing it to move two spaces(for white)
		if(move.fromRow == 6 && move.fromColumn == move.toColumn && move.fromRow-2 == move.toRow &&
				this.player() == Player.WHITE && board[move.toRow][move.toColumn] == null &&
				board[move.toRow+1][move.toColumn] == null){
			return true;
		}
		//checks if this is the first time the pawns first movement, allowing it to move two spaces(for black)
		if(move.fromRow == 1 && move.fromColumn == move.toColumn && move.fromRow+2 == move.toRow &&
				this.player() == Player.BLACK && board[move.toRow][move.toColumn] == null &&
				board[move.toRow-1][move.toColumn] == null){
			return true;
		}
		//checks if the pawn is white and moving up one space
		if((Math.abs(move.fromRow-1) != move.toRow  || move.fromColumn != move.toColumn) && this.player() == Player.WHITE){
			valid = false;
		}
		//checks if the pawn is black and moving down one space
		if((Math.abs(move.fromRow+1) != move.toRow  || move.fromColumn != move.toColumn) && this.player() == Player.BLACK){
			valid = false;
		}
		//checks if the space the pawn is moving to is occupied
		if(board[move.toRow][move.fromColumn] != null){
			valid = false;
		}

		//If statements checking for the unique condition where a pawn is able to take an opponents piece
		if(move.fromRow-1 == move.toRow && move.fromColumn+1 == move.toColumn && board[move.toRow][move.fromColumn+1] != null
				&& this.player() == Player.WHITE && board[move.toRow][move.fromColumn+1].player() == Player.BLACK){
			valid = true;
		}
		if (move.fromRow-1 == move.toRow && move.fromColumn-1 == move.toColumn && board[move.toRow][move.fromColumn-1] != null
				&& this.player() ==Player.WHITE && board[move.toRow][move.fromColumn-1].player()== Player.BLACK ) {
			valid = true;
		}
		if(move.fromRow+1 == move.toRow && move.fromColumn+1 == move.toColumn && board[move.toRow][move.fromColumn+1] != null
				&& this.player() == Player.BLACK && board[move.toRow][move.fromColumn+1].player() == Player.WHITE){
			valid = true;
		}
		if(move.fromRow+1 == move.toRow && move.fromColumn-1 == move.toColumn && board[move.toRow][move.fromColumn-1] != null
				&& this.player() == Player.BLACK && board[move.toRow][move.fromColumn-1].player() == Player.WHITE){
			valid = true;
		}

		return valid;
	}
}