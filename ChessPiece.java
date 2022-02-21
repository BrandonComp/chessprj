
public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	public Player player() {
		return owner;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;
		//if it is within boundaries
		if (move.fromRow == move.toRow && move.fromColumn == move.toColumn)
			return false;
		if (board[move.toRow][move.toColumn] != null){
			if(board[move.toRow][move.toColumn].player() == this.owner){
				return false;
			}
		}
		if (move.toRow < 0 || move.toRow > 7 || move.toColumn < 0 || move.toColumn  > 7){
			return false;
		}

		return valid;
	}
}
