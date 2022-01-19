package qwirkle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PlayGameRulesTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Board board;

    @BeforeEach
    public void setUp() throws Exception {
        player1 = new Player(4,new Random(3));
        player2 = player1.getNeighbour();
        player3 = player2.getNeighbour();
        player4 = player3.getNeighbour();
        board = player1.getBoard();
    }

    @Test
    public void a_tile_cant_be_placed_if_it_isnt_adjacent_to_an_existing_tile() {
        player1.playTile(0, 1, 1);
        player1.confirmTurn();
        Tile startTile = player2.getTile(1);

        player2.playTile(1, 2, 2);

        assertEquals(null, board.getTile(2,2));
        assertEquals(startTile.getColour(), player2.getTile(1).getColour());
        assertEquals(startTile.getShape(), player2.getTile(1).getShape());
    }

    @Test
    public void a_tile_can_only_be_added_to_a_series_if_it_is_in_line() {
        player1.playTile(2, 1, 1);
        player1.playTile(4, 1, 2);
        Tile startTile = player1.getTile(5);
        player1.playTile(5, 2, 2);

        assertEquals(null, board.getTile(2,2));
        assertEquals(startTile.getColour(), player1.getTile(5).getColour());
        assertEquals(startTile.getShape(), player1.getTile(5).getShape());
    }

}
