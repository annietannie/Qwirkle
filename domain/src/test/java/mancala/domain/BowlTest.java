package mancala.domain;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class BowlTest {

    @Test 
    public void aNormalBowlStartsWith4Stones() {
        Player mufasa = new Player();
        int aantalSteentjes = mufasa.getNthBowl(1).getStones();
        assertEquals(4, aantalSteentjes);
    }

    @Test 
    public void playABowlResultsIn0Stones() {
        Player mufasa = new Player();
        Bowl bowl = ((Bowl) mufasa.getNthBowl(1));
        bowl.playBowl();
        int aantalSteentjes = bowl.getStones();
        assertEquals(0, aantalSteentjes);
    }
    
    @Test 
    public void aBowlHas5StonesAfterReceiveStones() {
        Player mufasa = new Player();
        Bowl bowl = ((Bowl) mufasa.getNthBowl(1));
        int startStones = bowl.getStones();
        bowl.receiveStones(3);
        int endStones = bowl.getStones();
        assertEquals(startStones + 1, endStones);
    }

    @Test
    public void theBowlsNeighbourHas4Stones() {
        Player mufasa = new Player();
        Bowl bowl = ((Bowl) mufasa.getNthBowl(1));
        assertEquals(4, bowl.getNeighbour().getStones());
    }

    @Test
    public void theBowlsNeighbourHas1ExtraStoneAfterReceving() {
        Player mufasa = new Player();
        int startStones = ((Bowl) mufasa.getNthBowl(2)).getStones();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        int endStones = ((Bowl) mufasa.getNthBowl(2)).getStones();
        assertEquals(startStones + 1, endStones);
    }

    @Test
    public void theBowls4thNeighbourHas1ExtraStoneAfterPlayingBowl1() {
        Player mufasa = new Player();
        int startStones = ((Bowl) mufasa.getNthBowl(5)).getStones();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        int endStones = ((Bowl) mufasa.getNthBowl(5)).getStones();
        assertEquals(startStones + 1, endStones);
    }

    @Test
    public void theBowls5thNeighbourHasNoExtraStoneAfterPlayingBowl1() {
        Player mufasa = new Player();
        int startStones = ((Bowl) mufasa.getNthBowl(6)).getStones();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        int endStones = ((Bowl) mufasa.getNthBowl(6)).getStones();
        assertEquals(startStones, endStones);
    }

    @Test
    public void creatingTheFirstBowlAlsoCreatesPlayer1WhereMyTurnIsTrue() {
        Player mufasa = new Player();
        boolean turnKey = true;
        assertEquals(turnKey, mufasa.getMyTurn());
    }

    @Test
    public void theBowls5thNeighbourHasPlayer1WithMyTurnIsTrue() {
        Player mufasa = new Player();
        Player player = ((Bowl) mufasa.getNthBowl(6)).getPlayer();
        assertEquals(true, player.myTurn);
    }

    @Test
    public void theSixthBowlCreatesAKahalaWith0Stones() {
        Player mufasa = new Player();
        int aantalSteentjes = mufasa.getNthBowl(7).getStones();
        assertEquals(0, aantalSteentjes);
    } 

    @Test
    public void playingSecondBowlGivesThirdBowl5Stones() {
        Player mufasa = new Player();
        int startStones = ((Bowl) mufasa.getNthBowl(2)).getStones();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        int endStones = ((Bowl) mufasa.getNthBowl(2)).getStones();
        assertEquals(startStones + 1, endStones);
    }

    @Test
    public void playingThirdBowlGivesKahala1Stone() {
        Player mufasa = new Player();
        int startStones = mufasa.getNthBowl(7).getStones();
        ((Bowl) mufasa.getNthBowl(3)).playBowl();
        int endStones = mufasa.getNthBowl(7).getStones();
        assertEquals(1, endStones);
    } 

    @Test
    public void firstPlayerCreatesSecondPlayerWhoIsNotOnTurn() {
        Player mufasa = new Player();
        Player Simba = mufasa.getOpponent();
        assertEquals(false, Simba.getMyTurn());
    }
    
    @Test
    public void initialisingGivesKalaha1NeighbourPitWithPlayer2() {
        Player mufasa = new Player();
        Player Simba = mufasa.getNthBowl(8).getPlayer();
        assertEquals("Simba",Simba.getName());
    }

    /* @Test
    public void secondKalahasNeighbourHasPitnumber1() {
        Player mufasa = new Player();
        Pit kalaha2 = mufasa.getNthBowl(14);
        assertEquals(1, kalaha2.getNeighbour().getPitNumber());
    } */

    @Test
    public void afterTheFirstBowlIsPlayedTheTurnsChange() {
        Player mufasa = new Player();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        assertEquals(false, mufasa.getMyTurn());
        assertEquals(true, mufasa.getOpponent().getMyTurn());
    }

    @Test
    public void firstBowlOfSecondPlayerHasPitnumber8() {
        Player mufasa = new Player();
        Pit simbasFirstBowl = mufasa.getOpponent().myFirstBowl;
        assertEquals(8, simbasFirstBowl.getPitNumber());
    }

    @Test
    public void gameIsNotFinishedAfterFirstBowlIsPlayed() {
        Player mufasa = new Player();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        assertEquals(false, mufasa.endGame());
        assertEquals(false, mufasa.getOpponent().endGame());
    }

    @Test
    public void bowls12HasNoExtraStoneAfterPlayingBowl8() {
        Player mufasa = new Player();
        int startStones = ((Bowl) mufasa.getNthBowl(12)).getStones();
        ((Bowl) mufasa.getNthBowl(8)).playBowl();
        int endStones = ((Bowl) mufasa.getNthBowl(12)).getStones();
        assertEquals(startStones, endStones);
    }

    @Test
    public void bowls12Has1ExtraStoneAfterPlayingBowl8() {
        Player mufasa = new Player();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        int startStones = ((Bowl) mufasa.getNthBowl(12)).getStones();
        ((Bowl) mufasa.getNthBowl(8)).playBowl();
        int endStones = ((Bowl) mufasa.getNthBowl(12)).getStones();
        assertEquals(startStones + 1, endStones);
    }

    @Test
    public void whenPassingOpponentsKalahaItSkipsTheKalaha() {
        Player mufasa = Helper.setupToSkipKalaha();
        assertEquals(3, mufasa.getNthBowl(8).getStones());
        assertEquals(3, mufasa.getNthBowl(7).getStones());
    }

    @Test
    public void whenEndingOnAnEmptyBowlOnYourSideStealIfOppositeHasStones() {
        Player mufasa = Helper.setupToSteal();
        assertEquals(0, mufasa.getNthBowl(6).getStones());
        assertEquals(7, mufasa.getNthBowl(7).getStones());
        assertEquals(0, mufasa.getNthBowl(8).getStones());
    }
    
    @Test
    public void whenEndingOnAKahalaPlayer1CanPlayAgain() {
        Player mufasa = new Player();
        ((Bowl) mufasa.getNthBowl(3)).playBowl();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        assertEquals(5, ((Bowl) mufasa.getNthBowl(2)).getStones());
    }

    @Test
    public void whenSimbaIsEmptyMufasaAndSimbaReturnRespec31and17AsFinalScore() {
        Player mufasa = Helper.setupToEndGame();
        assertEquals(17, mufasa.getOpponent().getFinalScore());
        assertEquals(31, mufasa.getFinalScore());
    }

    @Test
    public void whenABowlOfTheNonActivePlayerIsPlayedNothingChanges() {
        Player mufasa = new Player();
        ((Bowl) mufasa.getNthBowl(8)).playBowl();
        assertEquals(4, ((Bowl) mufasa.getNthBowl(8)).getStones());
    }

    @Test
    public void whenAPlayerStealsWithItsLastStoneTheGameEnds() {
        Player mufasa = new Player();
        int[] setup = {0,1,0,0,0,0,0, 0,0,0,2,1,0,0};
        mufasa.endGameSimulator(setup);
        ((Bowl) mufasa.getNthBowl(2)).playBowl();
        assertEquals(3, mufasa.getFinalScore());
        assertEquals(1, mufasa.getOpponent().getFinalScore());
    }

    @Test
    public void whenAPlayerStealsOtherPlayersLastStoneTheGameEnds() {
        Player mufasa = new Player();
        int[] setup = {2,1,0,0,0,0,0, 0,0,0,1,0,0,0};
        mufasa.endGameSimulator(setup);
        ((Bowl) mufasa.getNthBowl(2)).playBowl();
        assertEquals(4, mufasa.getFinalScore());
        assertEquals(0, mufasa.getOpponent().getFinalScore());
    }
}