package tile;

public class TetrisTile extends Tile{
    public TetrisTile() {
        setExploder(new SelfExplode());
    }
}
