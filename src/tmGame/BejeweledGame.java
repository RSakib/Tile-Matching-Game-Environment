package tmGame;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import grid.Grid;
import grid.Position;
import grid.gravity.DropTilesDown;
import grid.matchingPatterns.HorizontalMatchingPattern;
import grid.matchingPatterns.IMatchingPattern;
import grid.matchingPatterns.LMatchingPattern;
import grid.matchingPatterns.Match;
import grid.matchingPatterns.TMatchingPattern;
import grid.matchingPatterns.VerticalMatchingPattern;
import tile.Color;
import tile.Tile;
import tile.BejeweledTiles.BejeweledTile;
import tile.BejeweledTiles.FlameTile;
import tile.BejeweledTiles.HypercubeTile;
import tile.BejeweledTiles.StarTile;
import tile.exploders.SameColorExplode;
import tmGame.InputHandler.BejeweledInputHandler;
import tmGame.InputHandler.InputHandlerJFX;
import tmGame.gameOverConditions.TimeUp;
import tmGame.gameScreen.BejeweledGameScreen;

public class BejeweledGame extends TileMatchingGame{
    private static int ROWS = 8;
    private static int COLS = 8;
    private static int SCORE_MULTIPLIER = 100;
    private static int GAME_LENGTH = 90;

    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE, Color.SILVER};
    private Random colorGenerator = new Random();
    private Position selectedPosition;

    public BejeweledGame() {
        initializeGame(
            new Grid(ROWS, COLS),
            new BejeweledGameScreen(), 
            new BejeweledInputHandler(),
            new TimeUp(Clock.systemUTC(), GAME_LENGTH),
            new IMatchingPattern[] {
                new HorizontalMatchingPattern(5),
                new VerticalMatchingPattern(5),
                new LMatchingPattern(3),
                new TMatchingPattern(3),
                new HorizontalMatchingPattern(4),
                new VerticalMatchingPattern(4),
                new HorizontalMatchingPattern(3),
                new VerticalMatchingPattern(3)
            },
            new DropTilesDown()
        );
        grid.setTile(new Position(1, 1), new HypercubeTile());
    }

    public BejeweledGame(InputHandlerJFX input) {
        initializeGame(
            new Grid(ROWS, COLS),
            new BejeweledGameScreen(), 
            input,
            new TimeUp(Clock.systemUTC(), GAME_LENGTH),
            new IMatchingPattern[] {
                new HorizontalMatchingPattern(5),
                new VerticalMatchingPattern(5),
                new LMatchingPattern(3),
                new TMatchingPattern(3),
                new HorizontalMatchingPattern(4),
                new VerticalMatchingPattern(4),
                new HorizontalMatchingPattern(3),
                new VerticalMatchingPattern(3)
            },
            new DropTilesDown()
        );
    }


    @Override
    public void onClockTick() {
        checkGameOver();
        applyGravity();
        fillEmptyTiles();
        matchTiles();
    }


    @Override
    public boolean matchTiles() {
        List<Position> allPositions = new ArrayList<>();
        for (int r = 0; r < grid.getNumRows(); r++) {
            for (int c = 0; c < grid.getNumCols(); c++) {
                allPositions.add(new Position(r, c));
            }
        }
        return matchTiles(allPositions);
    }


    public boolean swapTiles(Position p1, Position p2) {
        grid.swapTilesAt(p1, p2);
        List<Position> positionsToCheck = new ArrayList<>();
        positionsToCheck.add(p1);
        positionsToCheck.add(p2);
        
        // process hypercube switching 
        Tile t1 = grid.tileAt(p1);
        Tile t2 = grid.tileAt(p2);
        if (t1 instanceof HypercubeTile) {
            t2.setMatched(true);
            t1.setMatched(true);
            t2.setExploder(new SameColorExplode());
        }
        if (t2 instanceof HypercubeTile) {
            t2.setMatched(true);
            t1.setMatched(true);
            t1.setExploder(new SameColorExplode());
        }
        if (! matchTiles(positionsToCheck)) {
            // swap tiles back because no match
            grid.swapTilesAt(p1, p2);
            return false;
        }

        return true;
    }

    
    public Position selectedPosition() {
        return selectedPosition;
    }


    public void newSelectedPosition(Position p) {
        if (selectedPosition == null) {
            selectedPosition = p;
        } else if (selectedPosition.adjacent(p)) {
            swapTiles(p, selectedPosition);
            selectedPosition = null;
        } else {
            System.out.println("positions not adjacent");
            selectedPosition = p;
        }
    }


    private boolean matchTiles(List<Position> positionsToCheck) {
        Map<Position, Tile> powerupTiles = new HashMap<>();
        boolean foundMatch = false;

        // Mark matched tiles
        for (Position pos : positionsToCheck) {
            Tile tile = grid.tileAt(pos);
            if (! tile.isMatched()) {
                Match m = matchAt(pos);
                if (m.isMatch()) {
                    foundMatch = true;

                    for (Position p : m.getPositions()) {
                        grid.tileAt(p).setMatched(true);
                    }
                    // if match is horizontal or vertical 4, make powerup
                    IMatchingPattern matchPattern = m.getPattern();
                    if ((matchPattern instanceof HorizontalMatchingPattern || matchPattern instanceof VerticalMatchingPattern) && m.getNumMatched() == 4) {
                        powerupTiles.put(pos, new FlameTile(tile.getColor()));
                    }
                    if ((matchPattern instanceof HorizontalMatchingPattern || matchPattern instanceof VerticalMatchingPattern) && m.getNumMatched() == 5) {
                        powerupTiles.put(pos, new HypercubeTile());
                    }
                    if ((matchPattern instanceof LMatchingPattern || matchPattern instanceof TMatchingPattern)) {
                        powerupTiles.put(pos, new StarTile(tile.getColor()));
                    }
                }
            }
        }

        // explode tiles
        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int col = 0; col < grid.getNumCols(); col++) {
                Position pos = new Position(row, col);
                if (grid.tileAt(pos).isMatched()) {
                    List<Position> exploded = explodeAt(pos);
                    score += SCORE_MULTIPLIER * exploded.size();
                }
            }
        }

        grid.removeExplodedTiles();

        // add powerup tiles to grid
        powerupTiles.forEach((p, t) -> grid.setTile(p, t));

        return foundMatch; 
    }


    public void fillEmptyTiles() {
        //Fill empty spaces with new, random Bejeweled Tiles
        for(int row = 0; row < grid.getNumRows(); row++)
        {
            for(int col = 0; col < grid.getNumCols(); col++)
            {
                Position currPosition = new Position(row, col);
                if(grid.tileAt(currPosition).isEmpty())
                {
                    //set random bejeweled tile
                    int colorNum = colorGenerator.nextInt(7);
                    BejeweledTile newTile = new BejeweledTile(colors[colorNum]);
                    grid.setTile(currPosition, newTile);
                }
            }
        }
    }
    
}
