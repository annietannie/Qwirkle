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

	void confirmTurn(int player);

	void cancelTurn(int player);

	void tradeTile(int player, int index);

}
