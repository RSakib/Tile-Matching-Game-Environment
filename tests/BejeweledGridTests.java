package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grid.BejeweledGrid;
import grid.Grid;
import grid.Position;
import tile.Color;
import tile.EmptyTile;
import tile.SquareExplode;
import tile.BejeweledTiles.BejeweledTile;

public class BejeweledGridTests {
    private void assertGridEquals(int[][] expected, Grid grid){
        for (int r = 0; r < expected.length; r++) {
            for (int c = 0; c < expected[r].length; c++) {
                if (expected[r][c] != 0) {
                    assertEquals(Color.values()[expected[r][c] - 1], grid.tileAt(new Position(r, c)).getColor());
                }
            }
        }
    }

    private void setGrid(int[][] gridArray, Grid grid) {
        for (int r = 0; r < gridArray.length; r++) {
            for (int c = 0; c < gridArray[r].length; c++) {
                if (gridArray[r][c] == 0) {
                    grid.setTile(new Position(r, c), new EmptyTile());
                } else {
                    grid.setTile(new Position(r, c), new BejeweledTile(Color.values()[gridArray[r][c] - 1]));
                }
            }
        }
    }

    private BejeweledGrid grid;

    @Before
    public void setUp() {
        grid = new BejeweledGrid();
    }

    @Test
    public void BejeweledGridSetsProperly() {
        int[][] startGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        setGrid(startGrid, grid);
        assertGridEquals(endGrid, grid);
    }


    @Test
    public void MatchesThreeInHorizontalRow() {
        int[][] startGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {3, 4, 5, 6, 7, 1, 2, 3},
            {4, 5, 6, 7, 1, 2, 3, 4},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 3, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 2, 0, 0, 0, 6, 7, 1},
            {2, 3, 3, 4, 5, 7, 1, 2},
            {3, 4, 4, 5, 6, 1, 2, 3},
            {4, 5, 5, 6, 7, 2, 3, 4},
            {1, 2, 6, 7, 1, 6, 7, 1},
            {2, 3, 3, 4, 5, 7, 1, 2},
            {1, 2, 4, 5, 6, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        setGrid(startGrid, grid);
        int score = grid.matchTiles();
        assertEquals(BejeweledGrid.SCORE_MULTIPLIER*3, score);
        assertGridEquals(endGrid, grid);
    }


    @Test
    public void MatchesThreeInVerticalRow() {
        int[][] startGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 5, 6, 1, 1, 2},
            {3, 4, 5, 6, 7, 1, 2, 3},
            {4, 5, 6, 7, 1, 1, 3, 4},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 2, 3, 4, 5, 0, 7, 1},
            {2, 3, 4, 5, 6, 0, 1, 2},
            {3, 4, 5, 6, 7, 0, 2, 3},
            {4, 5, 6, 7, 1, 6, 3, 4},
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        setGrid(startGrid, grid);
        int score = grid.matchTiles();
        assertEquals(BejeweledGrid.SCORE_MULTIPLIER*3, score);
        assertGridEquals(endGrid, grid);
    }

    @Test
    public void PowerupTileCreatedWhenMatchOfFour() {
        int[][] startGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 5, 6, 1, 1, 2},
            {3, 4, 5, 6, 7, 1, 2, 3},
            {4, 5, 6, 7, 1, 1, 3, 4},
            {1, 2, 3, 4, 5, 1, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 2, 3, 4, 5, 0, 7, 1},
            {2, 3, 4, 5, 6, 0, 1, 2},
            {3, 4, 5, 6, 7, 0, 2, 3},
            {4, 5, 6, 7, 1, 6, 3, 4},
            {1, 2, 3, 4, 5, 1, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        setGrid(startGrid, grid);
        int score = grid.matchTiles();
        assertEquals(BejeweledGrid.SCORE_MULTIPLIER*4, score);
        assertGridEquals(endGrid, grid);
        assertTrue(grid.tileAt(new Position(4, 5)).getExploder() instanceof SquareExplode);
    }

    @Test
    public void SquareExplodeAfterFirstMatch() {
        int[][] startGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 1, 1, 3, 1, 2},
            {3, 4, 1, 1, 1, 1, 2, 3},
            {4, 5, 6, 7, 7, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] gridAfterFirstMatch = {
            {1, 2, 3, 0, 0, 0, 7, 1},
            {2, 3, 4, 4, 5, 6, 1, 2},
            {3, 4, 1, 1, 1, 3, 2, 3},
            {4, 5, 6, 7, 7, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 0, 0, 0, 0, 0, 7, 1},
            {2, 0, 0, 0, 0, 6, 1, 2},
            {3, 0, 0, 0, 5, 3, 2, 3},
            {4, 2, 3, 0, 7, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        setGrid(startGrid, grid);
        grid.matchTiles();
        assertGridEquals(gridAfterFirstMatch, grid);
        grid.matchTiles();
        assertGridEquals(endGrid, grid);
    }


    @Test
    public void ChainedExplosionsTriggered() {
        int[][] startGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 4, 7, 3, 1, 2},
            {3, 1, 1, 1, 5, 3, 2, 3},
            {4, 5, 6, 7, 7, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {0, 0, 0, 0, 5, 6, 7, 1},
            {0, 0, 0, 4, 7, 3, 1, 2},
            {0, 0, 0, 4, 5, 3, 2, 3},
            {0, 0, 3, 7, 7, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        setGrid(startGrid, grid);
        grid.tileAt(new Position(2, 1)).setExploder(new SquareExplode());
        grid.tileAt(new Position(3, 0)).setExploder(new SquareExplode());
        grid.matchTiles();
        assertGridEquals(endGrid, grid);
    }

    @Test
    public void SwappingTilesFourInRowCreatesPowerupAtSwapPosition() {
        int[][] startGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 1, 4, 7, 3, 1, 2},
            {3, 1, 5, 1, 1, 3, 2, 3},
            {4, 5, 6, 7, 7, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 0, 3, 0, 0, 6, 7, 1},
            {2, 2, 5, 4, 5, 3, 1, 2},
            {3, 3, 1, 4, 7, 3, 2, 3},
            {4, 5, 6, 7, 7, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        setGrid(startGrid, grid);
        Position p1 = new Position(1, 2);
        Position p2 = new Position(2, 2);
        grid.swapTiles(p1, p2);
        assertGridEquals(endGrid, grid);
        assertTrue(grid.tileAt(p2).getExploder() instanceof SquareExplode);
    }
}
