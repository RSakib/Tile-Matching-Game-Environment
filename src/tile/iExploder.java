//Imported Libraries
package tile;
import java.util.List;
import grid.Grid;


public interface iExploder {
    List<Integer>explode(Grid g, int row, int col);
}
