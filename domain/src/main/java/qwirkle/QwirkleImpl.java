package qwirkle;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class QwirkleImpl implements Qwirkle {
    private List<Player> players = new ArrayList<Player>();
    private HashMap<Integer, Player> playerNumber = new HashMap<Integer, Player>();
    public static QwirkleImpl qwirkle;
    
    public QwirkleImpl(int numberOfPlayers) {
        qwirkle = this;
        players.add(new Player(numberOfPlayers));
        playerNumber.put(1, players.get(0));
        for (int i=1; i<numberOfPlayers; i++) {
            players.add(players.get(i-1).getNeighbour());
            playerNumber.put(i, players.get(i));
        }
    }

    @Override
    public boolean isPlayersTurn(int player) {
        Player thisPlayersTurn = playerNumber.get(player);
        Boolean isItMyTurn = thisPlayersTurn.isMyTurn();
        return isItMyTurn;
    }

    /* @Override
    public boolean isEndOfGame() {
        boolean isGameIsOver = false;
        for (Player player : players) {
            isGameIsOver = player.isGameOver() ? true;
        }
        return isGameOver;
    } */
}
