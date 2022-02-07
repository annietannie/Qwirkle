package qwirkle.api.models;

public class Board {

    Tile[][] tiles;
    int[][] tileSeries;
    
    public Board(qwirkle.Qwirkle qwirkle, int xLength, int yLength) {
        this.tileSeries = new int[qwirkle.getTileSeriesSize()][2];
        for (int i=0; i<qwirkle.getTileSeriesSize(); i++) {
            this.tileSeries[i][0] = qwirkle.getTileSeriesNumbX(i);
            this.tileSeries[i][1] = qwirkle.getTileSeriesNumbY(i);
        }

        this.tiles = new Tile[yLength][xLength];
        for (int x=0; x<xLength; x++) {
            for (int y=0; y<yLength; y++) {
                if (qwirkle.isBoardTileNull(x, y)) {
                    tiles[y][x] = null;
                } else {
                    String shape = qwirkle.getBoardTileShape(x, y);
                    String colour = qwirkle.getBoardTileColour(x, y);
                    Boolean thisTurn = false;
                    for (int[] tile : tileSeries) {
                        if (tile[0] == x && tile[1] == y) {
                            thisTurn = true;
                        }
                    }
                    this.tiles[y][x] = new Tile(shape, colour, thisTurn);
                }
            }
        }
    }

    public Tile[][] getTiles() { return tiles; }
}
