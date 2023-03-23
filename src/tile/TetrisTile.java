package tile;

import tile.exploders.SelfExplode;
import tile.matchers.NonEmptyMatcher;

public class TetrisTile extends Tile{
    public TetrisTile(TileColor color) {
        setColor(color);
        setMatcher(new NonEmptyMatcher());
        setExploder(new SelfExplode());
    }
}
