package grid.IFallableBlocks;
import grid.Position;


public class TetrisIBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(0, -1), new Position(0, 0), new Position(0, 1), new Position(0, 2)},
        {new Position(-1, 1), new Position(0, 1), new Position(1, 1), new Position(2, 1)},
        {new Position(1, -1), new Position(1, 0), new Position(1, 1), new Position(1, 2)},
        {new Position(-1, 0), new Position(0, 0), new Position(1, 0), new Position(2, 0)},
    };

    public TetrisIBlock(Position spawnPosition){
        super(spawnPosition);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
}