package tile;

public class EmptyTile extends Tile {
    
    public EmptyTile() {
        setColor(Color.EMPTY);
        setMatcher(new NeverMatcher());
        setExploder(new NoExplode());
    }
}
