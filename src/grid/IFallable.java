package grid;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;

import tile.Tile;

public interface IFallable {
    public void rotateClockwise();
    public void rotateCounterClockwise();
    public void shift(Direction direction);
    public void fall();
    public boolean isFrozen();
    public void freeze();
    public Map<Position, Tile> getBlock();
}
