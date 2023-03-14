package grid;
import java.util.Map;

import tile.Tile;

public interface IFallable {
    public void rotate();
    public void shift();
    public void fall();
    public void freeze();
    public boolean isFrozen();
    public Map<Position, Tile> getBlock();
}
