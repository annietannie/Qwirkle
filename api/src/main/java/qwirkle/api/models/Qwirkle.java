package qwirkle.api.models;

public class Qwirkle {
    Player[] players;
    Board board;
    int numberOfTilesLeft;

    public Qwirkle(qwirkle.Qwirkle qwirkle, int numberOfPlayers) {
        this.players = new Player[numberOfPlayers];
        for (int i=0; i<numberOfPlayers; i++) {
            players[i] = new Player(qwirkle, i);
        }

        int xLength = qwirkle.getBoardSizeX();
        int yLength = qwirkle.getBoardSizeY();
        this.board = new Board(qwirkle, xLength, yLength);

        this.numberOfTilesLeft = qwirkle.getNumberOfTilesLeft();
    }

    public Player[] getPlayers() { return players; }


}
