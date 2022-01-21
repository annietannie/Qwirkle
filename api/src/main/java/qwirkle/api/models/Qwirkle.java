package qwirkle.api.models;

public class Qwirkle {
    Player[] players;
    Board gameBoard;
    int numberOfTilesLeft;
    Boolean isGameOver;

    public Qwirkle(qwirkle.Qwirkle qwirkle, int numberOfPlayers) {
        this.players = new Player[numberOfPlayers];
        for (int i=0; i<numberOfPlayers; i++) {
            players[i] = new Player(qwirkle, i);
        }

        int xLength = qwirkle.getBoardSizeX();
        int yLength = qwirkle.getBoardSizeY();
        this.gameBoard = new Board(qwirkle, xLength, yLength);

        this.numberOfTilesLeft = qwirkle.getNumberOfTilesLeft();
        this.isGameOver = qwirkle.isEndOfGame();
    }

    public Player[] getPlayers() { return players; }

    public Board getGameBoard() { return gameBoard; }

    public int getNumberOfTilesLeft() { return numberOfTilesLeft; }

    public Boolean getIsGameOver() { return isGameOver; }
}
