package tile;

import tile.exploders.SelfExplode;
import tile.matchers.NonEmptyMatcher;

public class TetrisTile extends Tile{
    public TetrisTile() {
        setMatcher(new NonEmptyMatcher());
        setExploder(new SelfExplode());
    }
}
