/**********************************************************************
 * Interface for the chess model which declares the methods
 * the classes which implement it will override.
 *
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version winter 2022
 *********************************************************************/

public interface IChessModel {


	/*****************************************************************
	 * Returns whether the game is complete.
	 *
	 * @return {@code true} if complete, {@code false} otherwise.
	 *****************************************************************/
	boolean isComplete();


	/*****************************************************************
	 * Returns whether the piece at location
	 * {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move an  object describing the move to be made.
	 * @return {@code true} if the proposed move is valid, {@code false} otherwise.
	 * or {@code [move.toRow, move.toColumn]} don't represent valid locations on the board.
	 *****************************************************************/
	boolean isValidMove(Move move);


	/*****************************************************************
	 * Moves the piece from location {@code [move.fromRow, move.fromColumn]}
	 * to location {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move a  object describing the move to be made.
	 *****************************************************************/
	void move(Move move);


	/*****************************************************************
	 * Report whether the current player p is in check.
	 * @param  p nthe Player being checked
	 * @return {@code true} if the current player is in check, {@code false} otherwise.
	 *****************************************************************/
	boolean inCheck(Player p);


	/*****************************************************************
	 * Return the current player.
	 *
	 * @return the current player
	 *****************************************************************/
	Player currentPlayer();

}
