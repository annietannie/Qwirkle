package mancala.domain;

public class Helper {

    public static Player setupToSkipKalaha() {
        Player mufasa = new Player();
        Player simba = mufasa.getOpponent();

        ((Bowl) mufasa.getNthBowl(5)).playBowl();
        ((Bowl) simba.getNthBowl(5)).playBowl();
        ((Bowl) mufasa.getNthBowl(1)).playBowl();
        ((Bowl) simba.getNthBowl(1)).playBowl();
        ((Bowl) mufasa.getNthBowl(2)).playBowl();
        ((Bowl) simba.getNthBowl(5)).playBowl();
        ((Bowl) mufasa.getNthBowl(6)).playBowl();
        ((Bowl) simba.getNthBowl(6)).playBowl();

        return mufasa;
    }
    
    public static Player setupToSteal() {
        Player mufasa = new Player();
        Player simba = mufasa.getOpponent();

        mufasa.getNthBowl(6).playBowl();
        simba.getNthBowl(3).playBowl();
        mufasa.getNthBowl(2).playBowl();

        return mufasa;
    }

    public static Player setupToEndGame() {
        /* This game results in:
        Mufasa: 31 stones
        Simba: 17 stones
        */

        Player mufasa = new Player();
        Player simba = mufasa.getOpponent();

        // mufasa.getNthBowl().playBowl();
        // simba.getNthBowl().playBowl();

        mufasa.getNthBowl(3).playBowl();
        mufasa.getNthBowl(6).playBowl();
        simba.getNthBowl(2).playBowl();
        simba.getNthBowl(1).playBowl();
        mufasa.getNthBowl(5).playBowl();
        simba.getNthBowl(2).playBowl();
        mufasa.getNthBowl(6).playBowl();
        mufasa.getNthBowl(4).playBowl();
        simba.getNthBowl(1).playBowl();
        mufasa.getNthBowl(6).playBowl();
        mufasa.getNthBowl(2).playBowl();
        simba.getNthBowl(3).playBowl();
        mufasa.getNthBowl(6).playBowl();
        simba.getNthBowl(1).playBowl();
        mufasa.getNthBowl(5).playBowl();
        simba.getNthBowl(2).playBowl();
        mufasa.getNthBowl(6).playBowl();
        mufasa.getNthBowl(4).playBowl();
        simba.getNthBowl(3).playBowl();
        mufasa.getNthBowl(3).playBowl();
        simba.getNthBowl(4).playBowl();
        mufasa.getNthBowl(6).playBowl();
        mufasa.getNthBowl(4).playBowl();
        simba.getNthBowl(6).playBowl();
        mufasa.getNthBowl(5).playBowl();
        mufasa.getNthBowl(6).playBowl();
        mufasa.getNthBowl(1).playBowl();
        simba.getNthBowl(1).playBowl();
        mufasa.getNthBowl(6).playBowl();
        mufasa.getNthBowl(4).playBowl();
        simba.getNthBowl(5).playBowl();
        mufasa.getNthBowl(4).playBowl();
        simba.getNthBowl(6).playBowl();

        return mufasa;
    }
}
