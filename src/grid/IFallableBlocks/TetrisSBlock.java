package grid.IFallableBlocks;
import grid.Position;
import tile.TileColor;


public class TetrisSBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(0, -1), new Position(0, 0), new Position(-1, 0), new Position(-1, 1)},
        {new Position(-1, 0), new Position(0, 0), new Position(0, 1), new Position(1, 1)},
        {new Position(0, 1), new Position(0, 0), new Position(1, 0), new Position(1, -1)},
        {new Position(1, 0), new Position(0, 0), new Position(0, -1), new Position(-1, -1)},
    };

    public TetrisSBlock(Position spawnPosition){
        super(spawnPosition, TileColor.GREEN);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
}