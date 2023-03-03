package tile;
import grid.Grid;
import java.util.List;
import java.util.ArrayList;
import grid.Position;

public class SameColorExplode implements iExploder{
    public List<Position>explode(Grid g, int row, int col)
    {
        //Work in progress
        ArrayList<Position> toExplode = new ArrayList<Position>();
        toExplode.add(new Position(row, col)); //Base
        //Use grid to get the color of the tile which explodes on the entire board
        for(int currRow= 0; currRow < g.getNumRows(); currRow++)
        {
            for(int currCol = 0; currCol < g.getNumCols(); currCol++)
            {
                //Get tile color
            }
        }
        return new ArrayList<Position>();
    }
}
