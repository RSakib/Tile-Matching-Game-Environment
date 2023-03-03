package tile;
import grid.Grid;
import java.util.List;
import java.util.ArrayList;
import grid.Position;

public class SquareExplode implements iExploder{
    public List<Position>explode(Grid g, int row, int col)
    {
        //Returns a list of positions that explodes all valid adjacent tiles to the list
        ArrayList<Position> toExplode = new ArrayList<Position>();
        toExplode.add(new Position(row, col));
        //Check all other adjacent tiles, only add valid tiles
        //Checks for bottom row
        if(g.validPosition(row+1, col)) toExplode.add(new Position(row+1, col));
        if(g.validPosition(row+1, col+1)) toExplode.add(new Position(row+1, col+1));
        if(g.validPosition(row+1, col-1)) toExplode.add(new Position(row+1, col-1));
        //Checks for top row
        if(g.validPosition(row-1, col)) toExplode.add(new Position(row-1, col));
        if(g.validPosition(row-1, col+1)) toExplode.add(new Position(row-1, col+1));
        if(g.validPosition(row-1, col-1)) toExplode.add(new Position(row-1, col-1));
        //Checks for current row
        if(g.validPosition(row, col+1)) toExplode.add(new Position(row, col+1));
        if(g.validPosition(row, col-1)) toExplode.add(new Position(row, col-1));
        return toExplode;
    }
}
