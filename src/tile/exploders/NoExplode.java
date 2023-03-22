package tile.exploders;
import java.util.List;
import java.util.ArrayList;
import grid.Grid;
import grid.Position;

public class NoExplode implements IExploder{
    public List<Position>explode(Grid g, int row, int col)
    {
        //Returns an empty list of ArrayList<Position>
        return new ArrayList<Position>();
    }
}
