package mancala.domain;

import java.io.*;
import java.util.*;

public class Player {
    protected boolean myTurn;
    protected Bowl myFirstBowl;
    protected String playerName;
    protected Player opponent;
    public int finalScore;

    public Player() {
        // Constructor for player Mufasa
        this.myTurn = true;
        this.playerName = "Mufasa";
        this.opponent = new Player(this);
        this.myFirstBowl = new Bowl(this, opponent);
    }

    protected Player(Player opponent) {
        // Constructor for player Simba
        this.myTurn = false;
        this.playerName = "Simba";
        this.opponent = opponent;
    }
    
    protected Player getOpponent() {
        return this.opponent;
    }

    protected boolean getMyTurn() {
        return myTurn;
    }

    public String getName() {
        return playerName;
    }

    protected void setMyFirstBowl(Bowl myFirstBowl) {
        this.myFirstBowl = myFirstBowl;
    }

    protected Pit getNthBowl(int index) {
        Pit nthBowl = this.myFirstBowl;
        if (index > 1) {
            nthBowl = nthBowl.getNeighbour().getNthBowl(index - 1);
            return nthBowl;
        }
        return nthBowl;
    }

    protected void switchTurn() {
        // The last active bowl tells its player to check for end game in order to switch turns.
        boolean theGameIsNotOverYet = !this.endGame();
        if (theGameIsNotOverYet) {
            // Switch your player's status and let his opponent do the same.
            this.myTurn = !this.myTurn; 
            this.opponent.switchTurn2();
        }
        else {
            // Let your player and its opponent obtain their final scores
            this.finalScore = this.getNthBowl(7).getStones();
            this.opponent.finishGame();
        }
    }

    protected void switchTurn2() {
        boolean theGameIsNotOverYet = !this.endGame();
        if (theGameIsNotOverYet) {
            // Switch your player's status
            this.myTurn = !this.myTurn; 
        }
        else {
           // Let your player and its opponent obtain their final scores
            this.finalScore = this.getNthBowl(7).getStones();
            this.opponent.finishGame();
        }
    }

    protected boolean endGame() {
        // Checking for endgame by asking all the bowls if they're emtpy.
        return this.myFirstBowl.areYouEmpty(true);
    }

    protected void finishGame() { 
        // Obtain the final score by asking the bowls what their scores are.
        this.finalScore = myFirstBowl.whatsYourScore(0);
    }

    protected int getFinalScore() {
        return this.finalScore;
    }

    protected void endGameSimulator(int[] setup) {
        /* Only for test purposes!
        */
        for (int i = 0; i < 14; i++) {
            this.getNthBowl(i+1).stones = setup[i];
        }
    }

}