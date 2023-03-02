package tile;
import java.util.List;
import java.util.ArrayList;
import grid.Grid;

public class NoExplode implements iExploder{
    public List<Integer>explode(Grid g, int row, int col)
    {
        return new ArrayList<Integer>();
    }
}
