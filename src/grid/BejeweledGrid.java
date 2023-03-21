//Stub BejeweledGrid class
package grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tile.Color;
import tile.EmptyTile;
import tile.IMatcher;
import tile.SameColorMatcher;
import tile.Tile;
import tile.BejeweledTiles.BejeweledTile;

public class BejeweledGrid extends Grid{

    public static int ROWS = 8;
    public static int COLS = 8;
    public static IMatcher IMATCHER = new SameColorMatcher();
    public static IMatchingPattern[] PATTERNS = {
        new HorizontalMatchingPattern(COLS),
        new VerticalMatchingPattern(ROWS)
    };
    private Color[] colors = {
        Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE, Color.WHITE};
    
    Random colorGenerator = new Random();

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

        //Calculate score
        int score = 100*explodedPositions.size(); // if no matches, returns 0
        // remove exploded tiles and calculate score
        if(score > 0)
        {
            for (Position p : explodedPositions) {
                setTile(p, new EmptyTile());
            }
            applyGravity();
        }
        return score; 
    }

    @Override
    public void applyGravity() {
        pullColumnsDown(); //Pull existing columns down
        //Fill empty spaces with new, random Bejeweled Tiles
        fillEmpty();
    }

    public matchTiles(Position original, Position swapped)
    {
        //Check swapped position for any matches
        if (! tileAt(swapped).isMatched()) {
            Match m = matchAt(swapped);
            if (! (m instanceof NoMatch)) {
                for (Position p : m.getPositions()) {
                    tileAt(p).setMatched(true);
                }
            }
        }
        //Check original for any matches
        if(! tileAt(original).isMatched())
        {
            Match m = matchAt(original);
            if(!(m instanceof NoMatch)){
                for(Position p : m.getPositions())
                {
                    tileAt(p).setMatched(true);
                }
            }
        }

        //Calculate tiles to explode
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

        //Calculate current score
        int currentScore = 100 * explodedPositions.size(); //stub

        //Look for power up tiles
        makeHorizontalPowerUp(explodedPositions, swapped, original);
        makeVerticalPowerUp(explodedPositions, swapped, original);

        //Explode remaining tiles
        for (Position p : explodedPositions) {
            setTile(p, new EmptyTile());
        }

        //Apply gravity
        applyGravity()

        //Loop through board to find natural matches. Repeat until clear
        int addedScore = matchTiles();
        while(addedScore != 0) //0 = no matches were found looping through board
        {
            currentScore += addedScore;
            addedScore = matchTiles();
        }
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
        Position below = currPosition;
        below.col += 1; //Position Below is column below
        Tile tileBelow = tileAt(below);
        if(tileBelow instanceof EmptyTile)
        {
            return true;
        }
        return false;
    }
    private Position getFloorPosition(Position currPosition)
    {
        Position floor = new Position(getNumRows()-1, currPosition.col);
        for(int currRow = currPosition.row; currRow < getNumRows(); currRow++)
        {
            if(!isBelowEmpty(currPosition))
            {
                floor = new Position(currRow+1, currPosition.col);
                return floor;
            }
        }
        return floor;
    }

    private void fillEmpty()
    {
        //Fills all empty tiles on the board to be random
        for(int row = 0; row < getNumRows(); row++)
        {
            for(int col = 0; col < getNumCols(); col++)
            {
                Position currPosition = new Position(row, col);
                if(tileAt(currPosition) instanceof EmptyTile)
                {
                    //set random bejeweled tile
                    int colorNum = colorGenerator.nextInt(0, 7);
                    BejeweledTile newTile = new BejeweledTile(colors[colorNum]);
                    setTile(currPosition, newTile);
                }
            }
        }
    }

    private void makeHorizontalPowerUp(List<Position> explodedPositions, Position swapped, Position original)
    {
        //Checks if swapped position is part of a power up match. Original used only for creating Same Color Explosion power up
        int horizontalMatched = 0;
        for(Position p: explodedPositions)
        {
            if(p.row == swapped.row)
            {
                horizontalMatched++;
            }
        }
        //Determine if horizontalBomb or PowerUp
        if(horizontalMatched >= 5)
        {
            //Same Color Explosion created
            SameColorPowerUp powerup = new SameColorPowerUp(tileAt(original).getColor());
            setTile(swapped, powerup);
            //Remove from exploded positions
            explodedPositions.remove(swapped);
        }
        else if(horizontalMatched >= 4)
        {
            SquareTilePowerUp powerup = new SquareTilePowerUp(tileAt(swapped).getColor());
            explodedPositions.remove(swapped);
        }
    }

    private void makeVeritcalPowerUp(List<Position> explodedPositions, Position swapped, Position original)
    {
                //Checks if swapped position is part of a power up match. Original used only for creating Same Color Explosion power up
                int verticalMatched = 0;
                for(Position p: explodedPositions)
                {
                    if(p.col == swapped.col)
                    {
                        veritcalMatched++;
                    }
                }
                //Determine if horizontalBomb or PowerUp
                if(verticalMatched >= 5)
                {
                    //Same Color Explosion created
                    SameColorPowerUp powerup = new SameColorPowerUp(tileAt(original).getColor());
                    setTile(swapped, powerup);
                    //Remove from exploded positions
                    explodedPositions.remove(swapped);
                }
                else if(verticalMatched >= 4)
                {
                    SquareTilePowerUp powerup = new SquareTilePowerUp(tileAt(swapped).getColor());
                    explodedPositions.remove(swapped);
                }
    }
}
