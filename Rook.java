/**********************************************************************
 * Creates the properties for a rook chess piece and determines its
 * valid movements.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public class Rook extends ChessPiece {

	/*****************************************************************
	 Constructor creates a rook chess piece and assigns the owner
	 of the piece.
	 @param player the owner of this piece
	 *****************************************************************/
	public Rook(Player player) { super(player); }


	/*****************************************************************
	 Returns the rook's title in a string format
	 @return "Rook"
	 *****************************************************************/
	public String type() { return "Rook"; }


	/*****************************************************************
	 Determines if the move is valid for a rook piece,
	 returning false if invalid, true if valid.
	 @param move the move to check
	 @param board the board the game is taking place on
	 @return true if valid, false if invalid
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		//calls the chesspiece isvalidmove method to check for generic validations
		if(!super.isValidMove(move, board))
			return false;
		boolean valid = true;

		//checks if the rook's row and column both changed, invalid if true
		if((move.fromRow != move.toRow && move.fromColumn != move.toColumn)){
			return false;
		}

		//integer used to determine if the rook is moving up or down
		int diff = move.toRow - move.fromRow;

		//if statements for checking if the rook will pass through a piece, returning invalid if true
		//moving up
		if(diff < 0){
			for(int i = -1; i > diff; i--){
				if(board[move.fromRow+i][move.fromColumn] != null){
					return false;
				}
			}
		}
		//moving down
		if(diff > 0){
			for(int i = 1; i < diff; i++){
				if(board[move.fromRow+i][move.fromColumn] != null){
					return false;
				}
			}
		}
		//integer used to determine if rook is moving left or right
		int diffc = move.toColumn - move.fromColumn;
		//moving left
		if(diffc < 0){
			for(int i = -1; i > diffc; i--){
				if(board[move.fromRow][move.fromColumn+i] != null){
					return false;
				}
			}
		}
		//moving right
		if(diffc > 0){
			for(int i = 1; i < diffc; i++){
				if(board[move.fromRow][move.fromColumn+i] != null){
					return false;
				}
			}
		}
        return valid;
		
	}
	
}
