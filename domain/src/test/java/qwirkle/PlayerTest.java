package qwirkle;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void Making_the_one_player_with_myTurn_is_true() {
        Player firstPlayer = new Player(1);
        assertTrue(firstPlayer.isMyTurn());
    }

    @Test
    public void Making_3_players_with_player_2_and_3_are_not_on_turn() {
        Player firstPlayer = new Player(3);
        Player secondPlayer = firstPlayer.getNextOpponent();
        Player thirdPlayer = secondPlayer.getNextOpponent();
        assertFalse(secondPlayer.isMyTurn());
        assertFalse(thirdPlayer.isMyTurn());
    }
}