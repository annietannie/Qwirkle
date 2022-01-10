package mancala.domain;
import java.util.HashMap;

public class MancalaImpl implements Mancala {
    private Player player1;
    private Player player2;
    private HashMap<Integer, Player> playerNumber = new HashMap<Integer, Player>();

    public MancalaImpl() {
        // Initialize the game here.
        this.player1 = new Player();
        this.player2 = player1.getOpponent();
        playerNumber.put(1, this.player1);
        playerNumber.put(2, this.player2);
    }

    @Override
    public boolean isPlayersTurn(int player) {
        Player thisPlayersTurn = playerNumber.get(player);
        Boolean isItMyTurn = thisPlayersTurn.getMyTurn();
        return isItMyTurn;
    }

    @Override
	public void playPit(int index) throws MancalaException {
        // Implement playing a pit.
        if (!player1.getNthBowl(index+1).playBowl()) {
            throw new MancalaException("This pit can't be played");
        }
    }
	
	@Override
	public int getStonesForPit(int index) {
        // Make a sane implementation.
        int numberOfStones = player1.getNthBowl(index+1).getStones();
        return numberOfStones;
    }

	@Override
	public boolean isEndOfGame() {
        boolean isP1Finished = player1.endGame();
        boolean isP2Finished = player2.endGame();
        return (isP1Finished || isP2Finished);
    }

	@Override
	public int getWinner() {
        int pointsP1 = player1.getFinalScore();
        int pointsP2 = player2.getFinalScore();
        if (isEndOfGame() == true) {
            if (pointsP1 > pointsP2) {
                return PLAYER_ONE;
            } else if (pointsP2 > pointsP1) {
                return PLAYER_TWO;
            }
            return BOTH_PLAYERS;
        } return NO_PLAYERS;
    }
}