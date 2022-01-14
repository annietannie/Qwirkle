package qwirkle;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class QwirkleImpl implements Qwirkle {
    private List<Player> players = new ArrayList<Player>();
    private Board board;
    private int numberOfTilesLeft;
    public static QwirkleImpl qwirkle;
    
    public QwirkleImpl(int numberOfPlayers) {
        qwirkle = this;
        players.add(new Player(numberOfPlayers));
        for (int i=1; i<numberOfPlayers; i++) {
            players.add(players.get(i-1).getNeighbour());
        }
        this.board = players.get(0).getBoard();
        this.numberOfTilesLeft = players.get(0).getTileBag().getNumberOfTiles();
    }

    @Override
    public boolean isPlayersTurn(int player) {
        Player thisPlayersTurn = players.get(player);
        Boolean isItMyTurn = thisPlayersTurn.isMyTurn();
        return isItMyTurn;
    }

    @Override
    public String getPlayerTileShape(int player, int index) {
        return players.get(player).getTile(index).getShape().toString();
    }

    @Override
    public String getPlayerTileColour(int player, int index) {
        return players.get(player).getTile(index).getColour().toString();
    }

    @Override
    public int getBoardSizeX() {
        return board.getTileGrid().get(0).size();
    }

    @Override
    public int getBoardSizeY() {
        return board.getTileGrid().size();
    }

    @Override
    public boolean isBoardTileNull(int x, int y) {
        return board.getTile(x,y) == null ? true : false;
    }

    @Override
    public String getBoardTileShape(int x, int y) {
        return board.getTile(x, y).getShape().toString();
    }

    @Override
    public String getBoardTileColour(int x, int y) {
        return board.getTile(x, y).getColour().toString();
    }

    @Override
    public int getNumberOfTilesLeft() {
        return numberOfTilesLeft;
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
