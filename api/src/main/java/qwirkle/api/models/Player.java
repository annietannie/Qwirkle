package qwirkle.api.models;

public class Player {

    boolean hasTurn;
    int score;
    Tile[] tiles;

    public Player(qwirkle.Qwirkle qwirkle, int playerNumber) {
        this.hasTurn = qwirkle.isPlayersTurn(playerNumber);
        this.score = qwirkle.getScore(playerNumber);

        this.tiles = new Tile[6];
        for (int i=0; i<6; i++) {
            if (qwirkle.isPlayerTileNull(playerNumber, i)) {
                this.tiles[i] = null;
            } else {
                String shape = qwirkle.getPlayerTileShape(playerNumber, i);
                String colour = qwirkle.getPlayerTileColour(playerNumber, i);
                Tile tile = new Tile(shape, colour);
                this.tiles[i] = tile;
            }
        }
    }

    public boolean getHasTurn() { return hasTurn; }

    public Tile[] getTiles() { return tiles; }

    public int getScore() { return score; }
}
