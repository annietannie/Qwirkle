package qwirkle.api.models;

public class Tile {

    String shape;
    String colour;
    Boolean thisTurn;

    public Tile(String shape, String colour, Boolean thisTurn) {
        this.shape = shape;
        this.colour = colour;
        this.thisTurn = thisTurn;
    }

    public String getShape() {
        return shape;
    }

    public String getColour() {
        return colour;
    }

    public Boolean getThisTurn() {
        return thisTurn;
    }
}
