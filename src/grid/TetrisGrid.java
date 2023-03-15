package grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tile.EmptyTile;
import tile.IMatcher;
import tile.NonEmptyMatcher;
import tile.Tile;

public class TetrisGrid extends FallingBlockGrid {
    public static int ROWS = 23;
    public static int COLS = 10;
    public static int[] ROWS_CLEARED_SCORE = { 0, 40, 100, 300, 1200};
    public static IMatcher MATCHER = new NonEmptyMatcher();
    public static IMatchingPattern[] PATTERNS = {
        new HorizontalMatchingPattern(COLS)
    };

    public TetrisGrid() {
        super(ROWS, COLS, MATCHER, PATTERNS);
    }

    @Override
    public IFallable createFaller() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFaller'");
    }

    @Override
    public void rotateFaller() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rotateFaller'");
    }

    @Override
    public void shiftFaller(Direction direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shiftFaller'");
    }

    @Override
    public int matchTiles() {
        // Mark matched tiles
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumCols(); col++) {
                Position pos = new Position(row, col);
                if (! tileAt(pos).isMatched()) {
                    Match m = matchAt(new Position(row, col));
                    if (! (m instanceof NoMatch)) {
                        for (Position p : m.getPositions()) {
                            tileAt(p).setMatched(true);
                        }
                    }
                }
            }
        }


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
        
        int rowsCleared = explodedPositions.size() / getNumCols();
        int score = scorePerRow(rowsCleared);

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
