package tmGame;

import java.time.Clock;
import java.util.Random;

import grid.BejeweledGrid;
import grid.Grid;
import grid.HorizontalMatchingPattern;
import grid.IMatchingPattern;
import grid.Position;
import grid.VerticalMatchingPattern;
import grid.IGravity.DropRowsDown;
import grid.IGravity.DropTilesDown;
import grid.IGravity.IGravity;
import tile.Color;
import tile.BejeweledTiles.BejeweledTile;
import tmGame.InputHandler.BejeweledInputHandler;
import tmGame.InputHandler.InputHandlerJFX;
import tmGame.gameOverConditions.GameOverCondition;
import tmGame.gameOverConditions.GridOverflowed;
import tmGame.gameOverConditions.TimeUp;
import tmGame.gameScreen.BejeweledGameScreen;
import tmGame.gameScreen.GameScreenJFX;

public class BejeweledGame extends TileMatchingGame{
    private static int ROWS = 8;
    private static int COLS = 8;
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE, Color.SILVER};
    private Random colorGenerator = new Random();

    public BejeweledGame() {
        super(
            new Grid(ROWS, COLS), 
            new BejeweledGameScreen(), 
            // new BejeweledInputHandler(this), 
            new TimeUp(Clock.systemUTC()), 
            new IMatchingPattern[] {
                new HorizontalMatchingPattern(5),
                new VerticalMatchingPattern(5),
                new HorizontalMatchingPattern(4),
                new VerticalMatchingPattern(4),
                new HorizontalMatchingPattern(3),
                new VerticalMatchingPattern(3)
            }, 
            new DropTilesDown()
        );
        setInputHandler(new BejeweledInputHandler(this));
    }


    @Override
    public void onClockTick() {
        applyGravity();
        fillEmptyTiles();
        matchTiles();
    }


    @Override
    public boolean matchTiles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchTiles'");
    }


    private void fillEmptyTiles() {
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
