package qwirkle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class StartGameTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    public void setUp() throws Exception {
        player1 = new Player(4,new Random(3));
        player2 = player1.getNeighbour();
        player3 = player2.getNeighbour();
        player4 = player3.getNeighbour();
    }

    @Test
    public void Making_the_one_player_with_myTurn_is_true_and_for_other_players_false() {
        assertTrue(player1.isMyTurn());
        assertFalse(player2.isMyTurn());
        assertFalse(player3.isMyTurn());
        assertFalse(player4.isMyTurn());
    }

    @Test
    public void Making_1_player_results_in_bag_with_102_tiles_and_player_has_6_tiles() {
        int numbTilesInBag = player1.getTileBag().getNumberOfTiles();
        List<Tile> tiles = player1.getTiles();
        /*for (Tile tile : tiles) {
            System.out.println(tile.getColour() + " " + tile.getShape());
        }*/
        assertEquals(84, numbTilesInBag);
        assertEquals(6, tiles.size());
    }

    @Test
    public void Neighbour_of_player4_has_yellow_diamond_as_first_tile() {
        Tile tileP1 = player1.getTile(0);
        Tile tileNeighbour = player4.getNeighbour().getTile(0);
        assertEquals(tileP1.getColour(), tileNeighbour.getColour());
        assertEquals(tileP1.getShape(), tileNeighbour.getShape());
    }
}