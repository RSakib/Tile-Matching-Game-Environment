//Stub BejeweledGrid class
package grid;

import java.util.ArrayList;
import java.util.List;

import tile.EmptyTile;
import tile.IMatcher;
import tile.SameColorMatcher;
import tile.Tile;

public class BejeweledGrid extends Grid{

    public static int ROWS = 8;
    public static int COLS = 8;
    public static IMatcher IMATCHER = new SameColorMatcher();
    public static IMatchingPattern[] PATTERNS = {
        new HorizontalMatchingPattern(COLS),
        new VerticalMatchingPattern(ROWS)
    };

    BejeweledGrid(int rows, int cols, IMatcher matcher, IMatchingPattern[] patterns)
    {
        super(rows, cols, matcher, patterns);
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
                //Need to check if power up blocks created as well
            }
        }

        // remove exploded tiles and calculate score
        for (Position p : explodedPositions) {
            setTile(p, new EmptyTile());
        }
        
        //Bejeweled Calculation is different from Tetris - Based on type of match
        //int rowsCleared = explodedPositions.size() / getNumCols();
        //int score = scorePerRow(rowsCleared);

        applyGravity();

        return 0; //stub return, modifications to Bejeweled score required
    }

    @Override
    public void applyGravity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyGravity'");
    }
    
}
