package mancala.domain;

abstract class Pit {
    protected int stones;
    protected int pitNumber;
    protected String pitType;
    protected Player player;
    protected Pit neighbourPit;
        
    protected int getStones() {
        return stones;
    }
    
    protected Pit getNeighbour() {
        return neighbourPit;
    }

    protected Pit getNthBowl(int index) {
        if (index == 1) {
            return this;
        }
        Pit nthBowl = this.neighbourPit.getNthBowl(index-1);
        return nthBowl;
    }

    protected Player getPlayer() {
        return player;
    }

    protected int getPitNumber() {
        return pitNumber;
    }

    protected boolean playBowl() {
        return false;
    }

    protected void receiveStones(int receivedStones) {}

    protected boolean areYouEmpty(boolean emptiness) {
        return false;
    }

    protected int stealStones() {
        return 0;
    }

    protected void takeTheseStolenStones(int stolenStones) {}

    protected int whatsYourScore(int score) {
        return 0;
    }

    protected Pit countToKalaha(int index) {
        return null;
    }
    protected Pit getToOpposite(int index) {
        return null;        
    }

}
