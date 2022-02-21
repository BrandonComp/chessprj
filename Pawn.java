public class Pawn extends ChessPiece {

	public Pawn(Player player) {
		super(player);
	}

	public String type() {
		return "Pawn";
	}
	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;
		if(!super.isValidMove(move, board)) {
			valid = false;
		}
		if((Math.abs(move.fromRow-1) != move.toRow  || move.fromColumn != move.toColumn) && this.player() == Player.WHITE){
			valid = false;
		}
		if((Math.abs(move.fromRow+1) != move.toRow  || move.fromColumn != move.toColumn) && this.player() == Player.BLACK){
			valid = false;
		}
		if(board[move.toRow][move.fromColumn] != null){
			valid = false;
		}
		//If statements for checking if the pawn is able to take opponents piece
		if(move.fromRow-1 == move.toRow && move.fromColumn+1 == move.toColumn && board[move.toRow][move.fromColumn+1] != null
				&& this.player() == Player.WHITE && board[move.toRow][move.fromColumn+1].player() == Player.BLACK){
			valid = true;
		}
		if (move.fromRow-1 == move.toRow && move.fromColumn-1 == move.toColumn &&
				board[move.toRow][move.fromColumn-1] != null && this.player() != board[move.toRow][move.fromColumn-1].player()) {
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

		//if space diagnol contains piece of opposite color

		return valid;
	}
}
