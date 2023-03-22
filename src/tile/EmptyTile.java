package tile;

import tile.exploders.NoExplode;
import tile.matchers.NeverMatcher;

public class EmptyTile extends Tile {
    
    public EmptyTile() {
        setColor(Color.EMPTY);
        setMatcher(new NeverMatcher());
        setExploder(new NoExplode());
    }
}
