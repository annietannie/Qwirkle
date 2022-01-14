package qwirkle.api.models;

public class Player {

    boolean hasTurn;
    Tile[] tiles;

    public Player(qwirkle.Qwirkle qwirkle, int playerNumber) {
        this.hasTurn = qwirkle.isPlayersTurn(playerNumber);
        this.tiles = new Tile[6];
        for (int i=0; i<6; i++) {
            String shape = qwirkle.getPlayerTileShape(playerNumber, i);
            String colour = qwirkle.getPlayerTileColour(playerNumber, i);
            Tile tile = new Tile(shape, colour);
        }
    }

    public boolean getHasTurn() { return hasTurn; }

    public Tile[] getTiles() { return tiles; }
}
