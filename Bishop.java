public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "Bishop";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(!super.isValidMove(move, board))
			return false;
		try {
			if (Math.abs(move.fromRow - move.toRow) / Math.abs(move.fromColumn - move.toColumn) != 1) {
				return false;
			}
		}
		catch(ArithmeticException e){
			return false;
		}

		int diff = move.toRow - move.fromRow;
		if(diff < 0){
			for(int i = -1; i > diff; i--){
				if(board[move.fromRow+i][move.fromColumn+i] != null){
					return false;
				}
			}
		}
		if(diff > 0){
			for(int i = 1; i < diff; i++){
				if(board[move.fromRow+i][move.fromColumn+i] != null){
					return false;
				}
			}
		}
		if(diff > 0){
			for(int i = 1; i < diff; i++){
				if(board[move.fromRow-i][move.fromColumn+i] != null){
					return false;
				}
			}
		}
		//works until here
		//index out of bounds if trying to cross over from more then one space away
//		if(diff < 0) {
//			for(int i = -1; i < diff; i--) {
//				if (board[move.fromRow - i][move.fromColumn + i] != null) {
//					return false;
//				}
//			}
//		}
		return true;
		
	}
}

//incheck
//find other player king first
//scan board for not null spaces and if they are of your color
//check for all  your pieces if it is a valid move for them to move to king space.
//create move to go to king space and check if it is valid
//then display in check popup
