package grid.IFallableBlocks;
import grid.Position;
import tile.TileColor;


public class TetrisZBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(-1, -1), new Position(-1, 0), new Position(0, 0), new Position(0, 1)},
        {new Position(-1, 1), new Position(0, 1), new Position(0, 0), new Position(1, 0)},
        {new Position(1, 1), new Position(1, 0), new Position(0, 0), new Position(0, -1)},
        {new Position(1, -1), new Position(0, -1), new Position(0, 0), new Position(-1, 0)},
    };

    public TetrisZBlock(Position spawnPosition){
        super(spawnPosition, TileColor.RED);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
}