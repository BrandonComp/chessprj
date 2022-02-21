public class Rook extends ChessPiece {

	public Rook(Player player) {
		
		super(player);
		
	}

	public String type() {
		
		return "Rook";
		
	}
	
	// determines if the move is valid for a rook piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(!super.isValidMove(move, board))
			return false;
		boolean valid = true;
		if((move.fromRow != move.toRow && move.fromColumn != move.toColumn)){
			return false;
		}
		int diff = move.toRow - move.fromRow;
		if(diff < 0){
			for(int i = -1; i > diff; i--){
				if(board[move.fromRow+i][move.fromColumn] != null){
					return false;
				}
			}
		}
		if(diff > 0){
			for(int i = 1; i < diff; i++){
				if(board[move.fromRow+i][move.fromColumn] != null){
					return false;
				}
			}
		}
		int diffc = move.toColumn - move.fromColumn;
		if(diffc < 0){
			for(int i = -1; i > diffc; i--){
				if(board[move.fromRow][move.fromColumn+i] != null){
					return false;
				}
			}
		}
		if(diffc > 0){
			for(int i = 1; i < diffc; i++){
				if(board[move.fromRow][move.fromColumn+i] != null){
					return false;
				}
			}
		}
        // More code is needed
        return valid;
		
	}
	
}
