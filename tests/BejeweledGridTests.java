package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grid.Grid;
import grid.Position;
import tile.Color;
import tile.EmptyTile;
import tile.BejeweledTiles.BejeweledTile;
import tile.BejeweledTiles.FlameTile;
import tile.BejeweledTiles.StarTile;
import tile.exploders.SquareExplode;
import tmGame.BejeweledGame;
import tmGame.TileMatchingGame;
import tmGame.InputHandler.InputHandlerJFX;

public class BejeweledGridTests {
    private class NullInputHandler implements InputHandlerJFX {
        @Override
        public void register(TileMatchingGame game) {
        }
    }

    private void assertGridEquals(int[][] expected, Grid grid){
        for (int r = 0; r < expected.length; r++) {
            for (int c = 0; c < expected[r].length; c++) {
                Position p = new Position(r, c);
                if (expected[r][c] != 0) {
                    assertEquals("mismatch at position" + p, Color.values()[expected[r][c] - 1], grid.tileAt(p).getColor());
                }
            }
        }

        // assert no positions on grid share references
        for (int r = 0; r < expected.length; r++) {
            for (int c = 0; c < expected[r].length; c++) {
                for (int i = 0; i < expected.length; i++) {
                    for (int j = 0; j < expected.length; j++) {
                        Position p1 = new Position(r, c);
                        Position p2 = new Position(i, j);
                        if (! p1.equals(p2)) {
                            assertFalse(grid.tileAt(p1).equals(grid.tileAt(p2)));
                        }
                    }
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

    private void matchTilesCycle(BejeweledGame game) {
        game.matchTiles();
        game.applyGravity();
        game.fillEmptyTiles();
    }

    private BejeweledGame game;
    private Grid grid;

    @Before
    public void setUp() {
        game = new BejeweledGame(new NullInputHandler());
        game.setInputHandler(null);
        grid = game.getGrid();
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
        matchTilesCycle(game);
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
        matchTilesCycle(game);
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
        matchTilesCycle(game);
        assertGridEquals(endGrid, grid);
        assertTrue(grid.tileAt(new Position(4, 5)) instanceof FlameTile);
    }

    @Test
    public void SquareExplodeAfterFirstMatch() {
        int[][] startGrid = {
            {1, 2, 3, 4, 5, 6, 7, 1},
            {2, 3, 4, 1, 1, 3, 1, 2},
            {3, 4, 1, 1, 1, 1, 2, 3},
            {4, 5, 6, 7, 2, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 6, 7, 3, 4, 7, 1},
            {6, 4, 3, 4, 5, 6, 7, 1}
        };

        int[][] gridAfterFirstMatch = {
            {1, 2, 3, 0, 0, 0, 7, 1},
            {2, 3, 4, 4, 5, 6, 1, 2},
            {3, 4, 1, 1, 1, 3, 2, 3},
            {4, 5, 6, 7, 2, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 6, 7, 3, 4, 7, 1},
            {6, 4, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 0, 0, 0, 0, 0, 7, 1},
            {2, 0, 0, 0, 0, 6, 1, 2},
            {3, 0, 0, 0, 5, 3, 2, 3},
            {4, 2, 3, 0, 2, 6, 3, 4},
            {1, 2, 3, 4, 5, 2, 7, 1},
            {2, 3, 4, 5, 6, 7, 1, 2},
            {1, 2, 6, 7, 3, 4, 7, 1},
            {6, 4, 3, 4, 5, 6, 7, 1}
        };

        setGrid(startGrid, grid);
        matchTilesCycle(game);
        assertGridEquals(gridAfterFirstMatch, grid);
        assertTrue(grid.tileAt(new Position(2, 2)) instanceof FlameTile);
        matchTilesCycle(game);
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
        matchTilesCycle(game);
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
        game.swapTiles(p1, p2);
        matchTilesCycle(game);
        assertTrue(grid.tileAt(p2) instanceof FlameTile);
        assertGridEquals(endGrid, grid);
    }

    @Test
    public void CanSwapTilesInMiddleOfFiveInARow() {
        int[][] startGrid = {
            {1, 2, 3, 1, 5, 6, 7, 1},
            {2, 3, 1, 1, 7, 3, 1, 2},
            {3, 1, 5, 7, 1, 3, 2, 3},
            {4, 5, 6, 1, 4, 6, 3, 4},
            {1, 2, 3, 1, 5, 2, 7, 1},
            {2, 3, 4, 4, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 2, 3, 0, 5, 6, 7, 1},
            {2, 3, 1, 0, 7, 3, 1, 2},
            {3, 1, 5, 0, 1, 3, 2, 3},
            {4, 5, 6, 1, 4, 6, 3, 4},
            {1, 2, 3, 7, 5, 2, 7, 1},
            {2, 3, 4, 4, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };


        setGrid(startGrid, grid);
        Position p1 = new Position(1, 3);
        Position p2 = new Position(2, 3);
        game.swapTiles(p1, p2);
        matchTilesCycle(game);
        assertGridEquals(endGrid, grid);
    }


    @Test
    public void LShapedPatternCreatesStarTile() {
        int[][] startGrid = {
            {1, 2, 3, 1, 5, 6, 7, 1},
            {2, 3, 6, 4, 7, 3, 1, 2},
            {3, 1, 1, 7, 1, 3, 2, 3},
            {4, 5, 6, 1, 4, 6, 3, 4},
            {1, 2, 3, 1, 5, 2, 7, 1},
            {2, 3, 4, 4, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };

        int[][] endGrid = {
            {1, 0, 0, 0, 5, 6, 7, 1},
            {2, 2, 3, 0, 7, 3, 1, 2},
            {3, 3, 6, 1, 7, 3, 2, 3},
            {4, 5, 6, 4, 4, 6, 3, 4},
            {1, 2, 3, 1, 5, 2, 7, 1},
            {2, 3, 4, 4, 6, 7, 1, 2},
            {1, 2, 3, 7, 3, 6, 7, 1},
            {1, 2, 3, 4, 5, 6, 7, 1}
        };


        setGrid(startGrid, grid);
        Position p1 = new Position(2, 3);
        Position p2 = new Position(2, 4);
        game.swapTiles(p1, p2);
        matchTilesCycle(game);
        assertGridEquals(endGrid, grid);
        assertTrue(grid.tileAt(new Position(4, 3)) instanceof StarTile);
    }
}
