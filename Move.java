/**********************************************************************
 * Creates a move which contains the "from" location and the "to"
 * location.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public class Move {

	/** values for the move's from position and to position  */
	public int fromRow, fromColumn, toRow, toColumn;


	/*****************************************************************
	 Default constructor which creates an empty move
	 *****************************************************************/
	public Move() {
	}


	/*****************************************************************
	 Constructor which creates a move from the entered values
	 @param fromRow the row the piece is moving from
	 @param fromColumn the column the piece is moving from
	 @param toRow the row the piece is moving to
	 @param toColumn the column the piece is moving to
	 *****************************************************************/
	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}


	/*****************************************************************
	 Converts the move to a string format
	 @return the string
	 *****************************************************************/
	@Override
	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
				+ "]";
	}
	
	
}
