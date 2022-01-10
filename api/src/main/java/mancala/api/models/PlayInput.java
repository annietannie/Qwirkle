package mancala.api.models;

public class PlayInput {

    int index;

    public int getIndex() {
        return index;
    }
    
    public void setIndex(String indexStr) {
        this.index = Integer.parseInt(indexStr);
    }
}
