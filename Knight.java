public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	public String type() {
		return "Knight";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board){
		if(!super.isValidMove(move, board))
			return false;
		boolean valid = true;
		if(Math.abs(move.fromRow-move.toRow) == 2) {
			if (Math.abs(move.fromRow - move.toRow) != 2 || Math.abs(move.fromColumn - move.toColumn) != 1) {
				valid = false;
			}
		}
		else if(Math.abs(move.fromRow-move.toRow) != 1 || Math.abs(move.fromColumn-move.toColumn) != 2){
			valid = false;
		}
		return valid;
		
	}

}
