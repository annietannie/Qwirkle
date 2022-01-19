package qwirkle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PlayGameTest {

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
    public void Placing_tile_0_from_player_1_results_in_that_tile_on_1_1_of_the_grid_and_gone_from_players_hand() {
        player1.playTile(0, 1, 1);
        assertEquals(null, player1.getTile(0));
        assertEquals(Colour.YELLOW, board.getTile(1,1).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,1).getShape());

        assertTrue(player1.isMyTurn());
    }

    @Test
    public void placing_tile_0_from_player_2_does_nothing() {
        player2.playTile(0,1,1);
        Tile startTile = player2.getTile(0);
        assertEquals(null, board.getTile(1,1));
        assertEquals(startTile.getColour(), player2.getTile(0).getColour());
        assertEquals(startTile.getShape(), player2.getTile(0).getShape());

        assertTrue(player1.isMyTurn());
    }

    @Test
    public void Placing_and_confirming_tile_0_from_player_1_results_in_that_tile_on_1_1_of_the_grid_and_new_tile_in_players_hand() {
        player1.playTile(0, 1,1);
        player1.confirmTurn();
        // For test purposes only
        /*Tile newTile = player1.getTile(0);
        System.out.println(newTile.getColour() + " " + newTile.getShape());*/

        assertEquals(Colour.RED, player1.getTile(0).getColour());
        assertEquals(Shape.DIAMOND, player1.getTile(0).getShape());

        assertEquals(Colour.YELLOW, board.getTile(1,1).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,1).getShape());

        assertFalse(player1.isMyTurn());
        assertTrue(player2.isMyTurn());
    }

    @Test
    public void Player2_cancelling_move_player1_does_not_have_any_effect() {
        player1.playTile(0, 1,1);
        player2.cancelTurn();

        assertEquals(null, player1.getTile(0));
        assertEquals(Colour.YELLOW, board.getTile(1,1).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,1).getShape());

        assertTrue(player1.isMyTurn());
    }

    @Test
    public void Placing_tile_and_cancelling_move_results_in_no_stone_in_1_1_on_grid_and_yellow_diamond_in_0_in_player1_hand() {
        player1.playTile(0, 1,1);
        player1.cancelTurn();
        assertEquals(null, board.getTile(1,1));
        assertEquals(Colour.YELLOW, player1.getTile(0).getColour());
        assertEquals(Shape.DIAMOND, player1.getTile(0).getShape());

        assertTrue(player1.isMyTurn());
    }

    @Test
    public void Placing_2_tiles_from_player1_results_in_those_tile_on_the_grid_and_gone_from_players_hand() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 1,2);
        assertEquals(null, player1.getTile(0));
        assertEquals(null, player1.getTile(1));
        assertEquals(Colour.YELLOW, board.getTile(1,1).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,1).getShape());
        assertEquals(Colour.PURPLE, board.getTile(1,2).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,2).getShape());

        assertTrue(player1.isMyTurn());
    }

    @Test
    public void Placing_2_tiles_from_player1_results_in_larger_grid_down() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 1,2);

        int boardSizeY = board.getTileGrid().size();
        int boardSizeX = board.getTileGrid().get(0).size();

        assertEquals(3, boardSizeX);
        assertEquals(4, boardSizeY);
    }

    @Test
    public void Placing_2_tiles_from_player1_results_in_larger_grid_up() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 1,0);

        System.out.println("Adjacent to existing tiles: " + board.adjacentToExistingTiles(1,0) );

        int boardSizeY = board.getTileGrid().size();
        int boardSizeX = board.getTileGrid().get(0).size();

        assertEquals(3, boardSizeX);
        assertEquals(4, boardSizeY);
    }

    @Test
    public void Placing_2_tiles_from_player1_results_in_larger_grid_left() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 0,1);

        int boardSizeY = board.getTileGrid().size();
        int boardSizeX = board.getTileGrid().get(0).size();

        assertEquals(4, boardSizeX);
        assertEquals(3, boardSizeY);
    }

    @Test
    public void Placing_2_tiles_from_player1_results_in_larger_grid_right() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 2,1);

        int boardSizeY = board.getTileGrid().size();
        int boardSizeX = board.getTileGrid().get(0).size();

        assertEquals(4, boardSizeX);
        assertEquals(3, boardSizeY);
    }

    @Test
    public void Placing_and_cancelling_move_2_tiles_from_player1_results_not_in_larger_grid() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 2,1);
        player1.cancelTurn();

        int boardSizeY = board.getTileGrid().size();
        int boardSizeX = board.getTileGrid().get(0).size();

        assertEquals(3, boardSizeX);
        assertEquals(3, boardSizeY);
    }

    @Test
    public void Placing_and_confirming_2_tiles_from_player_1_results_in_those_tiles_on_the_grid_and_new_tiles_in_players_hand() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 1,2);
        player1.confirmTurn();

        /*// For test purposes only
        Tile newTile2 = player1.getTile(1);
        System.out.println(newTile2.getColour() + " " + newTile2.getShape());*/

        assertEquals(Colour.RED, player1.getTile(0).getColour());
        assertEquals(Shape.DIAMOND, player1.getTile(0).getShape());
        assertEquals(Colour.PURPLE, player1.getTile(1).getColour());
        assertEquals(Shape.SQUARE, player1.getTile(1).getShape());


        assertEquals(Colour.YELLOW, board.getTile(1,1).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,1).getShape());
        assertEquals(Colour.PURPLE, board.getTile(1,2).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,2).getShape());

        assertFalse(player1.isMyTurn());
        assertTrue(player2.isMyTurn());
    }

    @Test
    public void Player1_changes_tiles_1_and_3_and_gets_new_stones() {
        player1.changeTile(0);
        player1.changeTile(2);
        player1.confirmTurn();

        System.out.println(player1.changingTiles);

        assertEquals(Colour.RED, player1.getTile(0).getColour());
        assertEquals(Shape.DIAMOND, player1.getTile(0).getShape());
        assertEquals(Colour.PURPLE, player1.getTile(2).getColour());
        assertEquals(Shape.SQUARE, player1.getTile(2).getShape());

        assertFalse(player1.isMyTurn());
        assertTrue(player2.isMyTurn());
    }

    @Test
    public void Player1_cancels_tile_change_and_nothing_happens() {
        player1.changeTile(0);
        player1.changeTile(2);
        player1.cancelTurn();

        assertEquals(Colour.YELLOW, player1.getTile(0).getColour());
        assertEquals(Shape.DIAMOND, player1.getTile(0).getShape());
        assertEquals(Colour.GREEN, player1.getTile(2).getColour());
        assertEquals(Shape.EIGHTSTAR, player1.getTile(2).getShape());

        assertTrue(player1.isMyTurn());
    }

    @Test
    public void get_Player2_tiles() {
        List<Tile> tiles = player2.getTiles();
        System.out.println("Tiles Player 2");
        for (Tile tile : tiles) {
            System.out.println(tile.getColour() + " " + tile.getShape());
        }
    }

    @Test
    public void player2_placing_stone_on_another_results_in_first_stone_on_that_place() {
        player1.playTile(0, 1,1);
        player1.confirmTurn();
        player2.playTile(1, 1,1);

        assertEquals(Colour.YELLOW, board.getTile(1,1).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,1).getShape());
        assertEquals(Colour.BLUE, player2.getTile(1).getColour());
        assertEquals(Shape.DIAMOND, player2.getTile(1).getShape());

    }

    @Test
    public void placing_2nd_stone_on_first_results_in_first_stone_on_that_place() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 1,1);

        assertEquals(Colour.YELLOW, board.getTile(1,1).getColour());
        assertEquals(Shape.DIAMOND, board.getTile(1,1).getShape());
        assertEquals(Colour.PURPLE, player1.getTile(1).getColour());
        assertEquals(Shape.DIAMOND, player1.getTile(1).getShape());

    }

    @Test
    public void after_placing_2_tiles_there_are_2_less_tiles_in_the_tilebag() {
        int startingTilesInBag = player1.getTileBag().getNumberOfTiles();
        player1.playTile(0, 1,1);
        player1.playTile(1, 1,2);
        player1.confirmTurn();
        int endingTilesInBag = player1.getTileBag().getNumberOfTiles();
        
        assertEquals(startingTilesInBag - 2, endingTilesInBag);
    }

    @Test
    public void twe_times_canceling_after_placing_a_tile_results_in_no_changes() {
        player1.playTile(0, 1,1);
        player1.playTile(1, 1,2);
        player1.cancelTurn();
        player1.cancelTurn();

        int boardSizeY = board.getTileGrid().size();
        int boardSizeX = board.getTileGrid().get(0).size();

        assertEquals(3, boardSizeX);
        assertEquals(3, boardSizeY);
    }
}
