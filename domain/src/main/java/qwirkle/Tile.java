package qwirkle;

public class Tile {
    Shape shape;
    Colour colour;

    public Tile(Shape shape, Colour colour) {
        this.shape = shape;
        this.colour = colour;
    }

    public Shape getShape() {
        return shape;
    }

    public Colour getColour() {
        return colour;
    }
}
