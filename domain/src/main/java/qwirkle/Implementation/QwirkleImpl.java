package qwirkle;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class QwirkleImpl implements Qwirkle {
    private List<Player> players = new ArrayList<Player>();
    private Board board;
    public static QwirkleImpl qwirkle;
    
    public QwirkleImpl(int numberOfPlayers) {
        qwirkle = this;
        players.add(new Player(numberOfPlayers));
        for (int i=1; i<numberOfPlayers; i++) {
            players.add(players.get(i-1).getNeighbour());
        }
        this.board = players.get(0).getBoard();
    }

    @Override
    public boolean isPlayersTurn(int player) {
        Player thisPlayersTurn = players.get(player);
        Boolean isItMyTurn = thisPlayersTurn.isMyTurn();
        return isItMyTurn;
    }

    @Override
    public boolean isPlayerTileNull(int player, int index) {
        return players.get(player).getTile(index) == null ? true : false;
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
        return players.get(0).getTileBag().getNumberOfTiles();
    }

    @Override
    public void playTile(int player, int index, int x, int y) {
        players.get(player).playTile(index, x, y);
    }

    @Override
    public void confirmTurn(int player) {
        players.get(player).confirmTurn();
    };

    @Override
    public void cancelTurn(int player) {
        players.get(player).cancelTurn();
    }

    @Override
    public void tradeTile(int player, int index) {
        players.get(player).changeTile(index);
    }

    @Override
    public boolean isEndOfGame() {
        for (Player player : players) {
            if (player.isGameOver()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getScore(int player) {
        return players.get(player).getScore();
    }
}
