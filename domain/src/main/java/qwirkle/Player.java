package qwirkle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    protected boolean myTurn;
    protected Player neighbour;
    protected TileBag tileBag;
    protected List<Tile> tiles;
    protected Board board;
    protected List<List<Object>> changingTiles;

    public Player(int numberOfPlayers) {
        this(numberOfPlayers, new Random());
    }

    protected Player(int numberOfPlayers, Random randomSeed) {
        this.myTurn = true;
        this.tileBag = new TileBag(randomSeed);
        this.board = new Board();
        setupPlayer(numberOfPlayers, tileBag, board, this);
    }

    protected Player(int numberOfPlayers, TileBag tileBag, Board board, Player firstPlayer) {
        this.myTurn = false;
        this.tileBag = tileBag;
        this.board = board;
        setupPlayer(numberOfPlayers, tileBag, board, firstPlayer);

    }

    protected void setupPlayer(int numberOfPlayers, TileBag tileBag, Board board, Player firstPlayer) {
        this.tiles = new ArrayList<>(6);

        // Create hand and grab 6 tiles
        for (int i=0; i<6; i++) {
            this.tiles.add(null);
            grabTile(i);
        }

        // Create and/or set neighbour
        if (numberOfPlayers > 1) {
            numberOfPlayers--;
            this.neighbour = new Player(numberOfPlayers, tileBag, board, firstPlayer);
        } else {
            this.neighbour = firstPlayer;
        }
    }

    public void playTile(int index, int x, int y) {
        if (myTurn && changingTiles == null) {
            if (board.placeTile(tiles.get(index), x, y, index)) {
                tiles.set(index, null);
            }
        }
    }

    public void changeTile(int index) {
        if (myTurn && tileBag.getNumberOfTiles() > 0 && !board.doesMoveExist()) {
            if (changingTiles == null) {
                changingTiles = new ArrayList<>();
            }
            if (tileBag.getNumberOfTiles() > changingTiles.size()) {
                List<Object> changeThisTile = new ArrayList<>();
                changeThisTile.add(tiles.get(index));
                changeThisTile.add(index);
                changingTiles.add(changeThisTile);
                tiles.set(index, null);
            }
        }
    }

    public void confirmTurn() {
        if (myTurn) {
            if (board.doesMoveExist()) {
                confirmMove();
            } if (changingTiles != null) {
                confirmChanging();
            }
            endOfTurn();
        }
    }

    public void confirmChanging() {
        List<List<Object>> newTiles = tileBag.changeTheseTiles(changingTiles);
        for (List<Object> newTile : newTiles) {
            Tile tile = (Tile) newTile.get(0);
            int index = (int) newTile.get(1);
            setTile(tile, index);
        }
    }

    public void confirmMove() {
        board.confirmMove();
        for (int i=0; i<6; i++) {
            if (tiles.get(i) == null) {
                grabTile(i);
            }
        }
    }

    public void cancelTurn() {
        if (myTurn) {
            if (board.doesMoveExist()) {
                cancelMove();
            } if (changingTiles != null) {
                cancelChanging();
            }
        }
    }

    public void cancelMove() {
        List<List<Object>> resetList = board.cancelMove();
        if (resetList != null) {
            for (List<Object> resetTile : resetList) {
                Tile tile = (Tile) resetTile.get(0);
                int index = (int) resetTile.get(3);
                setTile(tile, index);
            }
        }
    }

    public void cancelChanging() {
        for (List<Object> resetTile : changingTiles) {
            Tile tile = (Tile) resetTile.get(0);
            int index = (int) resetTile.get(1);
            setTile(tile, index);
        }
        changingTiles = null;
        
        if (board.doesMoveExist()) {
            cancelMove();
        }
    }

    protected void endOfTurn() {
        // Reset temporary lists
        changingTiles = null;

        if (isGameOver()) {
            endOfGame();
        } else {
            // Pass turn
            setMyTurn(false);
            neighbour.setMyTurn((true));
        }
    }

    protected Boolean isGameOver() {
        return handIsEmpty() && tileBag.isEmpty();
    }

    protected Boolean handIsEmpty() {
        for (Tile tile : tiles) {
            if (tile instanceof Tile) {
                return false;
            }
        }
        return true;
    }

    protected void endOfGame() {
        setMyTurn(false);
    }

    public Boolean isMyTurn() {
        return myTurn;
    }

    protected void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    protected Player getNeighbour() {
        return neighbour;
    }

    protected TileBag getTileBag() {
        return tileBag;
    }

    protected void grabTile(int index) {
        tiles.set(index, getTileBag().handATile());
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int index) {
        return tiles.get(index);
    }

    public void setTile(Tile tile, int index) {
        tiles.set(index, tile);
    }

    public Board getBoard() {
        return board;
    }
}
