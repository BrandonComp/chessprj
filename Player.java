/**********************************************************************
 * Enum for managing the two types of players.
 *
 * @author Brandon Rodriguez and Brendon Werner
 * @version Winter 2022
 *********************************************************************/

public enum Player {
	BLACK, WHITE;


	/*****************************************************************
	 Return the player whose turn is next
	 @return WHITE or BLACK
	 *****************************************************************/
	public Player next() {
		if (this == BLACK)
			return WHITE;
		else
		 	return BLACK;
		
	//	return this == BLACK ? WHITE : BLACK;
	}
}