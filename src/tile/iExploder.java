//Imported Libraries
package tile;
import java.util.List;
import grid.Grid;
import grid.Position;


public interface iExploder {
    public List<Position>explode(Grid g, int row, int col);
}
