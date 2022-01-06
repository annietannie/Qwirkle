package qwirkle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TileBag {

    protected List<Tile> tiles;

    protected TileBag(Random randomSeed) {
        makeAndShuffle(randomSeed);
    }

    protected void makeAndShuffle(Random randomSeed) {
        tiles = new ArrayList<>();
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

    protected Tile handATile() {
        Tile tile = tiles.get(0);
        tiles.remove(0);
        return tile;
    }
}
