package mancala.domain;

public class Bowl extends Pit {

    protected Bowl(Player mufasa, Player simba) {
        // Making Mufasa's first bowl
        this.pitType = "Bowl";
        this.stones = 4;
        this.player = mufasa;
        this.pitNumber = 1;
        this.neighbourPit = new Bowl(this.pitNumber, mufasa, simba, this);
    }

    protected Bowl(int pitNumber, Player mufasa, Player simba, Bowl firstBowl) {
        this.pitType = "Bowl";
        this.stones = 4;
        this.pitNumber = pitNumber + 1;

        boolean thisBowlsAreFromMufasa = this.pitNumber < 6;
        boolean thisKahalIsFromMufasa = this.pitNumber == 6;
        boolean thisBowlsAreFromSimba = (this.pitNumber >= 7 && this.pitNumber < 13);
        boolean thisKahalIsFromSimba = this.pitNumber == 13;

        boolean thisBowlIsSimbasFirstBowl = this.pitNumber == 8;

        if (thisBowlsAreFromMufasa) {
            this.player = mufasa;
            this.neighbourPit = new Bowl(this.pitNumber, mufasa, simba, firstBowl);
        }

        else if (thisKahalIsFromMufasa) {
            this.player = mufasa;
            this.neighbourPit = new Kahala(this.pitNumber, mufasa, simba, firstBowl);
        } 

        else if (thisBowlsAreFromSimba) {
            this.player = simba;
            if (thisBowlIsSimbasFirstBowl) {
                // Telling Simba that this is his first bowl
                this.player.setMyFirstBowl(this);
            }
            this.neighbourPit = new Bowl(this.pitNumber, mufasa, simba, firstBowl);
        }

        else if (thisKahalIsFromSimba) {
            this.player = simba;
            this.neighbourPit = new Kahala(this.pitNumber, mufasa, simba, firstBowl);
        } 
    }

    public boolean playBowl() {
        boolean thisBowlCanBePlayed = (this.player.getMyTurn() && this.stones > 0);
        // Only when the respective player is on turn and the bowl is not empty, the bowl can be played
        if (thisBowlCanBePlayed) {
            int givingStones = stones;
            this.stones = 0;
            neighbourPit.receiveStones(givingStones);
            return true;
        } else {
            return false;
        }
    }

    protected void receiveStones(int receivedStones) {
        /* Receiving stones, taking off one stone and passing the rest until there
        are no stones left to pass.
        */
        int givingStones = receivedStones - 1;
        boolean stealingIsPossible = (this.stones == 0 && this.player.getMyTurn());
        this.stones = this.stones + 1;
        if (givingStones > 0) {
            neighbourPit.receiveStones(givingStones);
        }
        else {
            if (stealingIsPossible) {
                this.steal();
            }
            this.player.switchTurn();
        }
    }

    protected boolean areYouEmpty(boolean emptiness) {
        /* Check if the bowl is empty. If not, it returns a false. If empty, it
        askes his neighbour. 
        */
        boolean iAmEmtpy = this.stones == 0;
        if (iAmEmtpy) {
            return this.neighbourPit.areYouEmpty(true);
        }
        else {
            return false;
        }
    }

    protected int stealStones() {
        /* Attempt to steal on this bowl. The bowl passes his stones and set his
        stone variable to 0.
        */
        int stolenStones = this.stones;
        this.stones = 0;
        return stolenStones;
    }

    protected int whatsYourScore(int score) {
        // Adds his number of stones to the score counter and asks his neighbour to do the same 
        score = score + this.stones;
        this.stones = 0;
        int finalScore = neighbourPit.whatsYourScore(score);
        return finalScore;
    }

    private void steal() {
        Pit opposite = getOpposite();
        int stolenStones = opposite.stealStones();
        boolean oppositeBowlWasNotEmpty = stolenStones > 0;
        if (oppositeBowlWasNotEmpty) {
            stolenStones = stolenStones + this.stones;
            this.stones = 0;
            // Give stolen stones to Kalaha
            this.player.getNthBowl(7).takeTheseStolenStones(stolenStones);
        }
    }

    private Pit getOpposite() {
        Pit opposite = this.neighbourPit.countToKalaha(1);
        return opposite;
    }

    @Override
    protected Pit countToKalaha(int index) {
        Pit opposite = this.neighbourPit.countToKalaha(index + 1);
        return opposite;
    }

    @Override
    protected Pit getToOpposite(int index) {
        if (index == 0) {
            return this;
        }
        Pit opposite = this.neighbourPit.getToOpposite(index - 1);
        return opposite;
    }

}