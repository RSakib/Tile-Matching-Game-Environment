package tile;

import tile.exploders.NoExplode;
import tile.matchers.NeverMatcher;

public class EmptyTile extends Tile {
    
    public EmptyTile() {
        setColor(TileColor.EMPTY);
        setMatcher(new NeverMatcher());
        setExploder(new NoExplode());
    }
}
