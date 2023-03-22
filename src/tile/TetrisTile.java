package tile;

public class TetrisTile extends Tile{
    public TetrisTile() {
        setMatcher(new NonEmptyMatcher());
        setExploder(new SelfExplode());
    }
}
