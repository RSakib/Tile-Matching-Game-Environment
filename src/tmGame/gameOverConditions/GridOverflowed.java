package tmGame.gameOverConditions;
import grid.Grid;
import grid.Position;
import tile.EmptyTile;
import tile.Tile;

public class GridOverflowed implements GameOverCondition{
    private Grid grid;
    private int invisibleRows;

    public GridOverflowed(Grid grid, int invisibleRows)
    {
        this.grid = grid;
        this.invisibleRows = invisibleRows;
    }


    @Override
    public boolean isGameOver() {
        //Check if there are any blocks in the invisible rows
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