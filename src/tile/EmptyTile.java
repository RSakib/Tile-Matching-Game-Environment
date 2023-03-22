package tile;

public class EmptyTile extends Tile {
    
    public EmptyTile() {
        setColor(Color.EMPTY);
        setExploder(new NoExplode());
    }
}
