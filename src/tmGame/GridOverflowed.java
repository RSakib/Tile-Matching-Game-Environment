package tmGame;
import grid.Grid;
import grid.Position;
import tile.EmptyTile;
import tile.Tile;

public class GridOverflowed implements GameOverCondition{

    private Grid grid;
    private int highestRow;

    GridOverflowed(Grid grid, int highestRow)
    {
        this.grid = grid;
        this.highestRow = highestRow;
    }


    @Override
    public boolean isGameOver() {
        //Check if there are any blocks above the highest row. If so, return true.
        int maxCol = grid.getNumCols();
        for(int row = highestRow; row >= 0; row--)
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
