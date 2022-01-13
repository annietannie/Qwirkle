package qwirkle;

import java.util.ArrayList;
import java.util.List;

public class Board {

    protected List<List<Tile>> tileGrid;
    protected List<List<Tile>> tileGridTemp;
    protected List<List<Object>> tileSeries;

    protected Board() {
        this.tileGrid = new ArrayList<>();
        for (int y=0; y<3; y++) {
            List<Tile> row = new ArrayList<>();
            for (int x=0; x<3; x++) {
                row.add(null);
            }
            tileGrid.add(row);
        }
    }

    protected Boolean placeTile(Tile tile, int x, int y, int index) {
        if (overlapsTileInGrid(x, y) ) {
            //|| overlapsTileInSeries(x, y)
            return false;
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

    protected void confirmMove() {
        this.tileSeries.clear();
        tileGridTemp.clear();
    }

    protected List<List<Object>> cancelMove() {
        if (tileSeries != null) {
            List<List<Object>> resetList = new ArrayList<>();
            resetList.addAll(tileSeries);

            tileGrid = new ArrayList<>();
            tileGrid.addAll(tileGridTemp);
            tileGridTemp.clear();
            this.tileSeries.clear();
            return resetList;
        }
        return null;
    }

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

    protected void gridExtension(int x, int y) {
        if (x == 0) {
            for (List<Tile> row : tileGrid) {
                row.add(0,null);
            }
        } else if (x == tileGrid.get(0).size()-1) {
            for (List<Tile> row : tileGrid) {
                row.add(null);
            }
        } else if (y == 0) {
            tileGrid.add(0, emtpyRow());
        } else if (y == tileGrid.size()-1) {
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

    protected Boolean overlapsTileInGrid(int x, int y) {
        return tileGrid.get(y).get(x) instanceof Tile;
    }

    protected Boolean adjacentToAtLeastOneExistingTileInGrid(int x, int y) {
        Object upperNeighbour = tileGrid.get(y-1).get(x);
        Object underNeighbour = tileGrid.get(y+1).get(x);
        Object leftNeighbour = tileGrid.get(y).get(x-1);
        Object rightNeighbour = tileGrid.get(y).get(x+1);

        return (upperNeighbour instanceof Tile
            || underNeighbour instanceof Tile
            || leftNeighbour instanceof Tile
            || rightNeighbour instanceof Tile);
    }

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
