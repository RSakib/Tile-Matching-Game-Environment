package grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import grid.IFallableBlocks.IFallable;
import grid.IFallableBlocks.TetrisIBlock;
import grid.IFallableBlocks.TetrisJBlock;
import grid.IFallableBlocks.TetrisLBlock;
import grid.IFallableBlocks.TetrisOBlock;
import grid.IFallableBlocks.TetrisSBlock;
import grid.IFallableBlocks.TetrisTBlock;
import grid.IFallableBlocks.TetrisZBlock;
import tile.EmptyTile;
import tile.IMatcher;
import tile.NonEmptyMatcher;
import tile.TetrisTile;
import tile.Tile;

public class TetrisGrid extends FallingBlockGrid {
    public static int ROWS = 24;
    public static int COLS = 10;
    public static int[] ROWS_CLEARED_SCORE = { 0, 40, 100, 300, 1200};
    public static IMatcher MATCHER = new NonEmptyMatcher();
    public static IMatchingPattern[] PATTERNS = {
        new HorizontalMatchingPattern(COLS)
    };

    public TetrisGrid() {
        super(ROWS, COLS, MATCHER, PATTERNS);
        setCurrentFaller(createFaller());
    }

    @Override
    public IFallable createFaller() {


        IFallable newFaller;
        Random randomGenerator = new Random();
        Position spawnPosition = new Position(3, COLS/2-1);

        int i = randomGenerator.nextInt(7);
        if (i == 0) {
            newFaller = new TetrisOBlock(spawnPosition);
        } else if (i == 1) {
            newFaller = new TetrisIBlock(spawnPosition);
        } else if (i == 2) {
            newFaller = new TetrisLBlock(spawnPosition);
        } else if (i == 3) {
            newFaller = new TetrisJBlock(spawnPosition);
        } else if (i == 4) {
            newFaller = new TetrisSBlock(spawnPosition);
        } else if (i == 5){
            newFaller = new TetrisZBlock(spawnPosition);
        } else {
            newFaller = new TetrisTBlock(spawnPosition);
        }
        
        for (Position point : newFaller.getBlock().keySet()) {
            if (!(tileAt(point) instanceof EmptyTile)) {
                return null;
            }
        }
        return newFaller;
        
    }

    @Override
    public void rotateFaller() {
        // test if can rotateFaller
        IFallable currentFaller = getCurrentFaller();
        Set<Position> currentPositions = currentFaller.getBlock().keySet();
        currentFaller.rotateClockwise();
        Set<Position> rotatedPositions = currentFaller.getBlock().keySet();
        currentFaller.rotateCounterClockwise(); // undo rotation for tile checking

        for (Position p: rotatedPositions) {
            if (! currentPositions.contains(p) && (!validPosition(p) || !(tileAt(p) instanceof EmptyTile))) {
                // can't rotate faller
                System.out.println(p);
                return;
            }
        }

        currentFaller.rotateClockwise();
    }

    @Override
    public void shiftFaller(Direction direction) {
        // test if can shiftFaller
        IFallable currentFaller = getCurrentFaller();
        Set<Position> fallerPositions = currentFaller.getBlock().keySet();

        for (Position p : fallerPositions) {
            Position adjacent = p.translate(direction);
            if (! fallerPositions.contains(adjacent) && (!validPosition(adjacent) || ! (tileAt(adjacent) instanceof EmptyTile))) {
                // can't shift in direction
                return;
            }
        }

        currentFaller.shift(direction);
    }

    @Override
    public int matchTiles() {
        int score = 0;

        int rowsMatched = 0;
        // Mark matched tiles
        for (int row = 0; row < getNumRows(); row++) {
            Position pos = new Position(row, 0);
            if (! tileAt(pos).isMatched()) {
                Match m = matchAt(pos);
                if (! (m instanceof NoMatch)) {
                    rowsMatched += 1;
                    for (Position p : m.getPositions()) {
                        tileAt(p).setMatched(true);
                    }
                }
            }
        }

        score += scorePerRow(rowsMatched);


        // Calculate tiles to explode
        List<Position> explodedPositions = new ArrayList<Position>();
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumCols(); col++) {
                Position pos = new Position(row, col);
                Tile tile = tileAt(pos);
                if (tile.isMatched()) {
                    List<Position> exploded = getExplodedTiles(pos);
                    explodedPositions.addAll(exploded);
                }
            }
        }

        // remove exploded tiles and calculate score
        for (Position p : explodedPositions) {
            setTile(p, new EmptyTile());
        }
        
        applyGravity();

        return score;
    }

    /**
     * moves tiles above emty rows down, does not fill holes
     */
    @Override
    public void applyGravity() {
        for (int row = getNumRows() - 1; row >= 0; row--) {
            if (isEmptyRow(row)) {
                int nextNonEmptyRow = nextNonEmptyRow(row);
                if (nextNonEmptyRow == -1) {
                    return;
                }

                rowSwap(row, nextNonEmptyRow);
            }
        }
    }


    private int scorePerRow(int rowsCleared) {
        if (rowsCleared == 0) {
            return 0;
        } else if (rowsCleared == 1) {
            return 40;
        } else if (rowsCleared == 2) {
            return 100;
        } else if (rowsCleared == 3) {
            return 300;
        } else if (rowsCleared == 4) {
            return 1200;
        } else {
            return 1000000;
        }
    }
    

    private boolean isEmptyRow(int rowNum) {
        for (int i = 0; i < getNumCols(); i++) {
            if ( ! (tileAt(new Position(rowNum, i)) instanceof EmptyTile)) {
                return false;
            }
        }
        return true;
    }


    private int nextNonEmptyRow(int startRow) {
        for (int row = startRow - 1; row >= 0; row--) {
            if (! isEmptyRow(row)) {
                return row;
            }
        }
        return -1;
    }

    private void rowSwap(int r1, int r2) {
        for (int i = 0; i < getNumCols(); i++) {
            Position p1 = new Position(r1, i);
            Position p2 = new Position(r2, i);
            Tile t1 = tileAt(p1);
            Tile t2 = tileAt(p2);
            setTile(p1, t2);
            setTile(p2, t1);
        }
    }
}
