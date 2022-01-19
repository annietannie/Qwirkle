package qwirkle;

import java.util.ArrayList;
import java.util.List;

public class Board {

    protected List<List<Tile>> tileGrid;
    protected List<List<Tile>> tileGridTemp;
    protected List<List<Object>> tileSeries;

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
    }

    /* Placing a tile */
    protected Boolean placeTile(Tile tile, int x, int y, int index) {
        // check if move is valid
        if (!placementIsValid(x, y)) { return false; }

        // Handle move
        if (isFirstTileInLine()) {
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
    protected Boolean placementIsValid(int x, int y) {
        if (!isFirstMove()) {
            if (isFirstTileInLine()) {
                if (!adjacentToExistingTiles(x, y)) 
                { return false; }
            }
            if (
                overlapsTileInGrid(x, y) 
                || !isInLineWithSeries(x, y)) 
            { return false; }
        }
        return true;
    }


    protected Boolean isFirstMove() {
        if (tileGrid.size() == 3 && tileGrid.get(0).size() == 3) {
            for (List<Tile> tileRow : tileGrid) {
                for (Tile tile : tileRow) {
                    if (tile instanceof Tile) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    protected Boolean isFirstTileInLine() {
        return tileSeries == null;
    }

    protected Boolean overlapsTileInGrid(int x, int y) {
        return tileGrid.get(y).get(x) instanceof Tile;
    }

    protected Boolean adjacentToExistingTiles(int x, int y) {
        return getNeighbourTiles(x,y).size() > 0;
    }

    protected Boolean isInLineWithSeries(int x, int y) {
        if (tileSeries.size() > 1) {
            if (tileSeries.get(0).get(1) == tileSeries.get(1).get(1)) {
                // Vertically aligned
                int xCoord = (int) tileSeries.get(0).get(1);
                return xCoord == x;
            } else if ((int) tileSeries.get(0).get(2) == (int) tileSeries.get(1).get(2)) {
                // Horizontally aligned
                int yCoord = (int) tileSeries.get(0).get(2);
                return yCoord == y;
            }
        } else {
            if ((int) tileSeries.get(0).get(1) == x || (int) tileSeries.get(0).get(2) == y) {
                return true;
            }
        }
        return false;
    }

    /* protected Boolean fitsInSeries(int x, int y, Tile tile) {

    } */

    protected List<List<Object>> getNeighbourTiles(int x, int y) {
        List<List<Object>> neighbours = new ArrayList<>();
        List<List<Object>> neighbourInstructions = new ArrayList<>();
        
        if (y != tileGrid.size()-1) {
            List<Object> neighbourInstructionUnder = new ArrayList<>();
            neighbourInstructionUnder.add(x);
            neighbourInstructionUnder.add(y+1);
            neighbourInstructionUnder.add(Neighbour.UNDER);
            neighbourInstructions.add(neighbourInstructionUnder);
        }

        if (y != 0) {
            List<Object> neighbourInstructionUpper = new ArrayList<>();
            neighbourInstructionUpper.add(x);
            neighbourInstructionUpper.add(y-1);
            neighbourInstructionUpper.add(Neighbour.UPPER);
            neighbourInstructions.add(neighbourInstructionUpper);
        }

        if (x != 0) {
            List<Object> neighbourInstructionLeft = new ArrayList<>();
            neighbourInstructionLeft.add(x-1);
            neighbourInstructionLeft.add(y);
            neighbourInstructionLeft.add(Neighbour.LEFT);
            neighbourInstructions.add(neighbourInstructionLeft);
        }

        if (x != tileGrid.get(0).size()-1) {
            List<Object> neighbourInstructionRight = new ArrayList<>();
            neighbourInstructionRight.add(x+1);
            neighbourInstructionRight.add(y);
            neighbourInstructionRight.add(Neighbour.RIGHT);
            neighbourInstructions.add(neighbourInstructionRight);
        }

        for (List<Object> neighbourInstruction : neighbourInstructions ) {
            if (getNeighbour(neighbourInstruction) != null) {
                neighbours.add(getNeighbour(neighbourInstruction));
            }
        }

        return neighbours;
    }

    protected List<Object> getNeighbour(List<Object> instructions) {
        int x = (int) instructions.get(0);
        int y = (int) instructions.get(1);
        Neighbour position = (Neighbour) instructions.get(2);
        if (getTile(x, y) != null) {
            ArrayList<Object> neighbour = new ArrayList<>();
            neighbour.add(position);
            neighbour.add((Tile) getTile(x, y));
            return neighbour;
        }
        return null;
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
