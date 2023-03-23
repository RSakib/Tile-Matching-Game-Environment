package tile.exploders;
import grid.Grid;
import java.util.List;
import java.util.ArrayList;
import grid.Position;
import tile.Tile;

public class SameColorExplode implements IExploder{
    public List<Position>explode(Grid g, int row, int col)
    {
        //Work in progres
        ArrayList<Position> toExplode = new ArrayList<Position>();
        Position targetPosition = new Position(row, col);
        Tile targetTile = g.tileAt(targetPosition);
        toExplode.add(targetPosition); //Base
        //Use grid to get the color of the tile which explodes on the entire board
        for(int currRow= 0; currRow < g.getNumRows(); currRow++)
        {
            for(int currCol = 0; currCol < g.getNumCols(); currCol++)
            {
                //If colors are the same, add to list
                Position currPosition = new Position(currRow, currCol);
                Tile currTile = g.tileAt(currPosition);
                if(targetTile.getColor().equals(currTile.getColor()))
                {
                    toExplode.add(currPosition);
                }
            }
        }
        return toExplode;
    }
}
