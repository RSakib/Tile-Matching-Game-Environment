package tile;

public class EmptyTile extends Tile {
    
    public EmptyTile() {
        setExploder(new NoExplode());
    }
}
