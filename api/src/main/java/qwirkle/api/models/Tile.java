package qwirkle.api.models;

public class Tile {

    String shape;
    String colour;

    public Tile(String shape, String colour) {
        this.shape = shape;
        this.colour = colour;
    }

    public String getShape() {
        return shape;
    }

    public String getColour() {
        return colour;
    }
}
