//Imported Libraries
package tile.exploders;
import java.util.List;
import grid.Grid;
import grid.Position;


public interface IExploder {
    public List<Position>explode(Grid g, int row, int col);
}
