//Stub BejeweledGrid class
package grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import tile.Color;
import tile.EmptyTile;
import tile.IMatcher;
import tile.SameColorMatcher;
import tile.Tile;
import tile.BejeweledTiles.BejeweledTile;
import tile.BejeweledTiles.SameColorPowerUp;
import tile.BejeweledTiles.SquareTilePowerUp;

public class BejeweledGrid extends Grid{

    public static int ROWS = 8;
    public static int COLS = 8;
    public static IMatcher IMATCHER = new SameColorMatcher();
    public static IMatchingPattern[] PATTERNS = {
        new HorizontalMatchingPattern(4),
        new VerticalMatchingPattern(4),
        new HorizontalMatchingPattern(3),
        new VerticalMatchingPattern(3)
    };
    public static int SCORE_MULTIPLIER = 100;

    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE, Color.WHITE};
    private Random colorGenerator = new Random();

    public BejeweledGrid()
    {
        super(ROWS, COLS, IMATCHER, PATTERNS);
        fillEmpty();
    }

    @Override
    public int matchTiles() {
        List<Position> allPositions = new ArrayList<>();
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
                allPositions.add(new Position(r, c));
            }
        }
        return matchTiles(allPositions);
    }

    @Override
    public void applyGravity() {
        pullColumnsDown(); //Pull existing columns down
    }


    public int swapTiles(Position p1, Position p2) {
        // check if tiles are adjacent

        swapTilesAt(p1, p2);
        List<Position> positionsToCheck = new ArrayList<>();
        positionsToCheck.add(p1);
        positionsToCheck.add(p2);
        int score = matchTiles(positionsToCheck);
        if (score == 0) {
            // swap tiles back because no match
            swapTiles(p1, p2);
        }
        return score;
    }

    private int matchTiles(List<Position> positionsToCheck) {
        Map<Position, Tile> powerupTiles = new HashMap<>();

        // Mark matched tiles
        for (Position pos : positionsToCheck) {
            Tile tile = tileAt(pos);
            if (! tile.isMatched()) {
                Match m = matchAt(pos);
                if (! (m instanceof NoMatch)) {
                    for (Position p : m.getPositions()) {
                        tileAt(p).setMatched(true);
                    }
                    // if match is horizontal or vertical 4, make powerup
                    IMatchingPattern matchPattern = m.getPattern();
                    if ((matchPattern instanceof HorizontalMatchingPattern || matchPattern instanceof VerticalMatchingPattern) && m.getNumMatched() == 4) {
                        powerupTiles.put(pos, new SquareTilePowerUp(tile.getColor()));
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

        //Calculate score
        int score = SCORE_MULTIPLIER*explodedPositions.size(); // if no matches, returns 0
        // remove exploded tiles and calculate score
        if(score > 0)
        {
            for (Position p : explodedPositions) {
                setTile(p, new EmptyTile());
            }
            // add powerup tiles to grid
            powerupTiles.forEach((p, t) -> setTile(p, t));
            applyGravity();
            fillEmpty();
        }
        return score; 
    }


    private void pullColumnsDown()
    {
        for(int currRow = 0; currRow < getNumRows(); currRow++)
        {
            for(int currCol = 0; currCol < getNumCols(); currCol++)
            {
                ArrayList<Position>columnTiles = new ArrayList<Position>();
                Position currPosition = new Position(currRow, currCol);
                Tile currTile = tileAt(currPosition);
                if((!(currTile instanceof EmptyTile)) && isBelowEmpty(currPosition))
                {
                    columnTiles = getColumnOrder(currPosition);
                    Position floor = getFloorPosition(currPosition);
                    for(int i = 0; i < columnTiles.size(); i++)
                    {
                        setTile(floor, tileAt(columnTiles.get(i)));
                        floor.row--;
                    }
                }

            }
        }
    }

    private ArrayList<Position> getColumnOrder(Position currPosition)
    {
        ArrayList<Position>columnTiles = new ArrayList<Position>();
        //Loop to record order of the rest of the column
        for(int i = currPosition.row; i >= 0; i--)
        {
            Position rowPosition = new Position(i, currPosition.col);
            Tile colTile = tileAt(rowPosition);
            if(!(colTile instanceof EmptyTile))
            {
                columnTiles.add(rowPosition);
            }
        }
        return columnTiles;
    }
    private boolean isBelowEmpty(Position currPosition)
    {
        //Helper method to check if tile immediately below is empty
        Position below = currPosition.translate(Direction.DOWN);
        if(validPosition(below) && tileAt(below) instanceof EmptyTile)
        {
            return true;
        }
        return false;
    }
    private Position getFloorPosition(Position currPosition)
    {
        // Position floor = new Position(getNumRows()+1, currPosition.col);
        for(int currRow = currPosition.row + 1; currRow < getNumRows(); currRow++)
        {
            Position pos = new Position(currRow, currPosition.col);
            if(!isBelowEmpty(pos))
            {
                return pos;
            }
        }
        return currPosition;
    }

    private void fillEmpty()
    {
        //Fill empty spaces with new, random Bejeweled Tiles
        for(int row = 0; row < getNumRows(); row++)
        {
            for(int col = 0; col < getNumCols(); col++)
            {
                Position currPosition = new Position(row, col);
                if(tileAt(currPosition) instanceof EmptyTile)
                {
                    //set random bejeweled tile
                    int colorNum = colorGenerator.nextInt(7);
                    BejeweledTile newTile = new BejeweledTile(colors[colorNum]);
                    setTile(currPosition, newTile);
                }
            }
        }
    }
}
