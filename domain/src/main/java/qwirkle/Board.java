package qwirkle;

import java.util.ArrayList;
import java.util.List;

public class Board {

    protected List<List<Tile>> tileGrid;
    protected List<List<Tile>> tileGridTemp;
    protected List<List<Object>> tileSeries;
    protected Boolean isFirstMove;

    /* Constructing the board */
    protected Board() {
        this.tileGrid = new ArrayList<>();
        for (int y=0; y<3; y++) {
            List<Tile> row = new ArrayList<>();
            for (int x=0; x<3; x++) {
                row.add(null);
            }
            tileGrid.add(row);
        }
        this.isFirstMove = true;
    }

    /* Placing a tile */
    protected Boolean placeTile(Tile tile, int x, int y, int index) {
        if (isFirstMove) {
            this.isFirstMove = false;
        } else {
            if (overlapsTileInGrid(x, y) || !adjacentToExistingTiles(x, y)) {
            return false;
            }
        }

        if (tileSeries == null) {
            tileSeries = new ArrayList<>();
            createTemporaryGridCopy();
        }

        ArrayList<Object> newTile = new ArrayList<>();
        newTile.add(tile);
        newTile.add(x);
        newTile.add(y);
        newTile.add(index);
        tileSeries.add(newTile);

        tileGrid.get(y).set(x,tile);

        gridExtension(x, y);

        return true;
    }

    /* Confirming a move */
    protected void confirmMove() {
        this.tileSeries = null;
        tileGridTemp.clear();
    }

    /* Cancelling a move */
    protected List<List<Object>> cancelMove() {
        if (tileSeries != null) {
            List<List<Object>> resetList = new ArrayList<>();
            resetList.addAll(tileSeries);

            tileGrid = new ArrayList<>();
            tileGrid.addAll(tileGridTemp);
            tileGridTemp.clear();
            this.tileSeries = null;
            return resetList;
        }
        return null;
    }

    /* Making a copy of the old grid in case a move is cancelled */
    protected void createTemporaryGridCopy() {
        tileGridTemp = new ArrayList<>();
        for (List<Tile> gridRow : tileGrid) {
            List<Tile> tempRow = new ArrayList<>();
            for (Tile tileCopy : gridRow) {
                tempRow.add(tileCopy);
            }
            tileGridTemp.add(tempRow);
        }
    }

    /* Extending the grid when a tile is placed on a side */
    protected void gridExtension(int x, int y) {
        if (x == 0) {
            for (List<Tile> row : tileGrid) {
                row.add(0,null);
            }
        } if (x == tileGrid.get(0).size()-1) {
            for (List<Tile> row : tileGrid) {
                row.add(null);
            }
        } if (y == 0) {
            tileGrid.add(0, emtpyRow());
        } if (y == tileGrid.size()-1) {
            tileGrid.add(emtpyRow());
        }
    }

    protected List<Tile> emtpyRow() {
        List<Tile> emptyRow = new ArrayList<>();
        for (Tile tile : tileGrid.get(0)) {
            emptyRow.add(null);
        }
        return emptyRow;
    }

    /* Checks for placing a tile */

    protected Boolean overlapsTileInGrid(int x, int y) {
        return tileGrid.get(y).get(x) instanceof Tile;
    }

    protected Boolean adjacentToExistingTiles(int x, int y) {
        List<Object> neighbours = new ArrayList<>();
        if (y != 0) {
            Object upperNeighbour = tileGrid.get(y-1).get(x);
            System.out.println("I have an upper neighbour");
        }
        if (y != tileGrid.size()-1) {
            Object underNeighbour = tileGrid.get(y+1).get(x);
            System.out.println("I have an under neighbour");
        }
        if (x != 0) {
            Object leftNeighbour = tileGrid.get(y).get(x-1);
            System.out.println("I have a left neighbour");
        }
        if (x != tileGrid.get(0).size()-1) {
            Object rightNeighbour = tileGrid.get(y).get(x+1);
            System.out.println("I have a right neighbour");
        }

        for (Object neighbour : neighbours) {
            if (neighbour instanceof Tile) {
                return true;
            }
        }

        return false;
    }

    /* Getters */
    public List<List<Tile>> getTileGrid() {
        return tileGrid;
    }

    public Tile getTile(int x, int y) {
        return tileGrid.get(y).get(x);
    }

    public List<List<Object>> getTileSeries() {
        return tileSeries;
    }

    protected Boolean doesMoveExist() {
        return tileSeries != null;
    }


}
