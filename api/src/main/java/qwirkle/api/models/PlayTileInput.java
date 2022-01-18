package qwirkle.api.models;

public class PlayTileInput {
    int player;
    int selectedTile;
    int x;
    int y;


    public int getPlayer() {
        return player;
    }

    public int getSelectedTile() {
        return selectedTile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void setSelectedTile(int selectedTile) {
        this.selectedTile = selectedTile;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
