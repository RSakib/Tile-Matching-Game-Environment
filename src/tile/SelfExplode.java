package tile;
import grid.Grid;
import java.util.List;
import java.util.ArrayList;
import grid.Position;

public class SelfExplode implements iExploder{
    public List<Position>explode(Grid g, int row, int col)
    {
        //Only explodes a single tile
        ArrayList<Position> toExplode = new ArrayList<Position>();
        toExplode.add(new Position(row, col));
        return toExplode;
    }
}
