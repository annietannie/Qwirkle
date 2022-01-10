package mancala.domain;

public class Kahala extends Pit {

    protected Kahala(int pitNumber, Player mufasa, Player simba, Bowl firstBowl) {
        this.pitType = "Kahala";
        this.stones = 0;
        this.pitNumber = pitNumber + 1;
        // Making the first Kahala
        if (this.pitNumber == 7) {
            this.player = mufasa;
            this.neighbourPit = new Bowl(this.pitNumber, this.player, simba, firstBowl);
        }
        // Making the second Kahala and registering the first bowls as its neighbour
        if (this.pitNumber == 14) {
            this.player = simba;
            this.neighbourPit = firstBowl;
        }
    }

    @Override
    protected void receiveStones(int receivedStones) {
        /* The Kalaha can only receive bowls if his player is on turn. Otherwise it
        just passes the stones without taking one off.
        */
        boolean itsMyPlayersTurn = this.player.getMyTurn();
        if (itsMyPlayersTurn) {
            this.stones = this.stones + 1;
            int givingStones = receivedStones - 1;
            if (givingStones > 0) {
                neighbourPit.receiveStones(givingStones);
            }
            else {
                boolean theGameIsOver = this.player.endGame();
                if (theGameIsOver) {
                this.player.finishGame();
                this.player.getOpponent().finishGame();
                }
            }
        }
        else {
            int givingStones = receivedStones;
            neighbourPit.receiveStones(givingStones);
        }
    }

    @Override
    protected boolean areYouEmpty(boolean emptiness) {
        /* When the areYouEmpty method reaches the Kalaha it means that all bowls
        on this side are empty so it returns 'true'. This results in the end of
        the game.
        */
        return true;
    }

    @Override
    protected void takeTheseStolenStones(int stolenStones) {
        /* The Kahala receives the stolen stones and adds them to his own.
        */
        this.stones = this.stones + stolenStones;
    }

    @Override
    protected int whatsYourScore(int score) {
        /* Adds his number of stones to the score counter and returns this value
        */
        score = score + this.stones;
        this.stones = score;
        return score;
    }

    @Override
    protected Pit countToKalaha(int index) {
        Pit opposite = this.neighbourPit.getToOpposite(index-1);
        return opposite;
    }
}
