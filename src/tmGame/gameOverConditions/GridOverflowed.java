package tmGame.gameOverConditions;
import grid.Grid;
import grid.Position;
import tile.EmptyTile;
import tile.Tile;
import tmGame.TileMatchingGame;

public class GridOverflowed implements GameOverCondition{
    private int invisibleRows;

    public GridOverflowed(int invisibleRows)
    {
        this.invisibleRows = invisibleRows;
    }


    @Override
    public boolean isGameOver(TileMatchingGame game) {
        //Check if there are any blocks in the invisible rows
        Grid grid = game.getGrid();
        int maxCol = grid.getNumCols();
        for(int row = invisibleRows - 1; row >= 0; row--)
        {
            for(int col = 0; col < maxCol; col++)
            {
                Tile currTile = grid.tileAt(new Position(row, col));
                if(!(currTile instanceof EmptyTile))
                {
                    return true;
                }
            }
        }
        return false;
    }    
}