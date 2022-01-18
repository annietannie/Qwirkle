package qwirkle;

public interface Qwirkle {
    public static final int NO_PLAYERS = 0;

	/**
	 * Method indicating if the first player has the next turn or not.
     * @param The player which you want to know the turn for.
	 * @return True if the first player has the next turn, false if it's the turn of the other player.
	 */
	boolean isPlayersTurn(int player);

	boolean isPlayerTileNull(int player, int index);
	
	String getPlayerTileShape(int player, int index);

	String getPlayerTileColour(int player, int index);

	int getBoardSizeX();

	int getBoardSizeY();

	boolean isBoardTileNull(int x, int y);

	String getBoardTileShape(int x, int y);

	String getBoardTileColour(int x, int y);

	int getNumberOfTilesLeft();

	void playTile(int player, int index, int x, int y);

	/* void placeTile(int index, Player player) throws QwirkleException;

	void cancelMove(Player player);

	void confirmMove(Player player) throws QwirkleException;

	void changeTile(int index, Player player) throws QwirkleException;

	void confirmChange(Player player);

	void cancelChange(Player player) throws QwirkleException;
	
	boolean isEndOfGame(); */
}
