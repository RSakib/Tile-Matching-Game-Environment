package tmGame;
import grid.FallingBlockGrid;
import grid.Grid;
import grid.Position;
import tile.EmptyTile;
import tile.Tile;

public class GridOverflowed implements GameOverCondition{

    private FallingBlockGrid grid;

    GridOverflowed(FallingBlockGrid grid)
    {
        this.grid = grid;
    }


    @Override
    public boolean isGameOver() {
        //Check if there are any blocks above the highest row. If so, return true.
        int maxCol = grid.getNumCols();
        for(int row = 0; row <= 4; row++)
        {
            for(int col = 0; col < maxCol; col++)
            {
                Tile currTile = grid.tileAt(new Position(row, col));
                if(!(currTile instanceof EmptyTile) && !(grid.getCurrentFaller().blockPositions.contains(new Position(row, col))))
                {
                    return true;
                }
            }
        }
        return false;
    }    
}
