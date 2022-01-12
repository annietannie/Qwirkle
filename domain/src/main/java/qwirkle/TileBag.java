package qwirkle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TileBag {

    protected List<Tile> tiles;
    private Random randomSeed;

    protected TileBag(Random randomSeed) {
        makeAndShuffle(randomSeed);
    }

    protected void makeAndShuffle(Random randomSeed) {
        this.tiles = new ArrayList<>();
        this.randomSeed = randomSeed;
        for (Colour colour : Colour.values()) {
            for (Shape shape : Shape.values()) {
                for (int i=0; i<3; i++) {
                    tiles.add(new Tile(shape, colour));
                }
            }
        }
        Collections.shuffle(tiles, randomSeed);
    }

    public int getNumberOfTiles() {
        return tiles.size();
    }

    public Boolean isEmpty() {
        return getNumberOfTiles() == 0;
    }

    protected Tile handATile() {
        if (getNumberOfTiles() > 0) {
            Tile tile = tiles.get(0);
            tiles.remove(0);
            return tile;
        } else {
            return null;
        }
    }

    protected List<List<Object>> changeTheseTiles(List<List<Object>> changeTiles) {
        List<List<Object>> newTiles = new ArrayList<>(changeTiles.size());
        for (List<Object> oldTileList : changeTiles) {
            Tile oldTile = (Tile) oldTileList.get(0);
            int index = (int) oldTileList.get(1);
            tiles.add(oldTile);
            Tile newTile = tiles.get(0);
            tiles.remove(0);
            List<Object> newTileList = new ArrayList<>(2);
            newTileList.add(newTile);
            newTileList.add(index);
            newTiles.add(newTileList);
        }
        Collections.shuffle(tiles, randomSeed);
        return newTiles;
    }
}
