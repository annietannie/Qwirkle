package qwirkle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    protected boolean myTurn;
    protected Player neighbour;
    protected TileBag tileBag;
    protected List<Tile> tiles;

    public Player(int numberOfPlayers) {
        this(numberOfPlayers, new Random());
    }

    public Player(int numberOfPlayers, Random randomSeed) {
        this.myTurn = true;
        this.tileBag = new TileBag(randomSeed);
        setupPlayer(numberOfPlayers, tileBag, this);
    }

    public Player(int numberOfPlayers, TileBag tileBag, Player firstPlayer) {
        this.myTurn = false;
        this.tileBag = tileBag;
        setupPlayer(numberOfPlayers, tileBag, firstPlayer);

    }

    public void setupPlayer(int numberOfPlayers, TileBag tileBag, Player firstPlayer) {
        this.tiles = new ArrayList<>();
        grabTile(6);
        if (numberOfPlayers > 1) {
            int nextNumberOfPlayers = numberOfPlayers - 1;
            this.neighbour = new Player(nextNumberOfPlayers, tileBag, firstPlayer);
        } else {
            this.neighbour = firstPlayer;
        }
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public Player getNeighbour() {
        return neighbour;
    }

    public TileBag getTileBag() {
        return tileBag;
    }

    public void grabTile(int numberOfTiles) {
        for (int i=0; i<numberOfTiles; i++) {
            tiles.add(getTileBag().handATile());
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int index) {
        return tiles.get(index);
    }
}
