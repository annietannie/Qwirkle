package qwirkle;

public class Player {
    protected boolean myTurn;
    protected Player nextOpponent;
    //protected int score;


    public Player(int numberOfPlayers) {
        this.myTurn = true;
        if (numberOfPlayers > 1) {
            int nextNumberOfPlayers = numberOfPlayers - 1;
            this.nextOpponent = new Player(nextNumberOfPlayers, this);
        }
    }

    public Player(int numberOfPlayers, Player firstPlayer) {
        this.myTurn = false;
        if (numberOfPlayers > 1) {
            int nextNumberOfPlayers = numberOfPlayers - 1;
            this.nextOpponent = new Player(nextNumberOfPlayers, firstPlayer);
        } else {
            this.nextOpponent = firstPlayer;
        }
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public Player getNextOpponent() {
        return nextOpponent;
    }

}
