package qwirkle.api.models;

public class Board {

    Tile[][] grid;
    
    public Board(qwirkle.Qwirkle qwirkle, int xLength, int yLength) {
        this.grid = new Tile[yLength][xLength];
        for (int x=0; x<xLength; x++) {
            for (int y=0; y<yLength; y++) {
                if (qwirkle.isBoardTileNull(x, y)) {
                    grid[y][x] = null;
                } else {
                    String shape = qwirkle.getBoardTileShape(x, y);
                    String colour = qwirkle.getBoardTileColour(x, y);
                    Tile tile = new Tile(shape, colour);
                }
            }
        }
    }

    public Tile[][] getTiles() { return grid; }
}
