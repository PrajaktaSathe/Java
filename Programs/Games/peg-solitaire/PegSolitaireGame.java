public interface PegSolitaireGame{
	/**
	 * Method to create first board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	void Board1(boolean is_loaded);

	/**
	 * Method to create second board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	void Board2(boolean is_loaded);

	/**
	 * Method to create third board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	void Board3(boolean is_loaded);

	/**
	 * Method to create fourth board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	void Board4(boolean is_loaded);

	/**
	 * Method to create fifth board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	void Board5(boolean is_loaded);

	/**
	 * Method to create sixth board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	void Board6(boolean is_loaded);

	/**
	 * Method to add cell buttons to the frame for first 5 types.
	 */
	void addBoard();

	/**
	 * Method to add cell buttons to the frame for last type.
	 */
	void addBoardTriangle();

	/**
	 * Method to add listeners for the cell buttons.
	 */
	void addingListenersType();

	/**
	 * Method to determine if move is valid for the up.
	 * @param yRow Row value of the selected cell.
	 * @param yCol Column value of the selected cell.
	 * @param row Row value of the slot that user wants to go.
	 * @param col Column value of the slot that user wants to go.
	 * @return boolean to specify if the move is valid.
	 */
	boolean is_valid_up(int yRow, int yCol, int row, int col);

	/**
	 * Method to determine if move is valid for the down.
	 * @param yRow Row value of the selected cell.
	 * @param yCol Column value of the selected cell.
	 * @param row Row value of the slot that user wants to go.
	 * @param col Column value of the slot that user wants to go.
	 * @return boolean to specify if the move is valid.
	 */
	boolean is_valid_down(int yRow, int yCol, int row, int col);

	/**
	 * Method to determine if move is valid for the right.
	 * @param yRow Row value of the selected cell.
	 * @param yCol Column value of the selected cell.
	 * @param row Row value of the slot that user wants to go.
	 * @param col Column value of the slot that user wants to go.
	 * @return boolean to specify if the move is valid.
	 */
	boolean is_valid_right(int yRow, int yCol, int row, int col);

	/**
	 * Method to determine if move is valid for the left.
	 * @param yRow Row value of the selected cell.
	 * @param yCol Column value of the selected cell.
	 * @param row Row value of the slot that user wants to go.
	 * @param col Column value of the slot that user wants to go.
	 * @return boolean to specify if the move is valid.
	 */
	boolean is_valid_left(int yRow, int yCol, int row, int col);

	/**
	 * Method to determine if the game is finished.
	 * @return boolean to specify if the game is finished.
	 */
	boolean is_finished();

	/**
	 * Method to refresh the current screen.
	 */
	void reset_screen();

	/**
	 * Method to make the computer play 1 move.
	 * @return boolean to specify if the game is finished.
	 */
	boolean autoOne();
}