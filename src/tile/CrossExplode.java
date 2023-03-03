package tile;
import grid.Grid;
import java.util.List;
import java.util.ArrayList;
import grid.Position;

public class CrossExplode implements iExploder{
    public List<Position>explode(Grid g, int row, int col)
    {
                //Returns a list of the positions that consist of the entire row and column of the match to explode
        ArrayList<Position> toExplode = new ArrayList<Position>();
        //Retrieve entire column to be erased
        for(int i = 0; i < g.getNumRows(); i++)
        {
            //Retrieve all elements in the column and add them to the list
            toExplode.add(new Position(i, col));
        }
        //Retrieve entire row to be erased
        for(int i = 0; i < g.getNumCols(); i++)
        {
            toExplode.add(new Position(row, i));
        }
        return toExplode;
    }
}
