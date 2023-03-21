package grid.IFallableBlocks;

import grid.Position;

public class TetrisJBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(-1, -1), new Position(0, -1), new Position(0, 0), new Position(0, 1)},
        {new Position(-1, 1), new Position(-1, 0), new Position(0, 0), new Position(1, 0)},
        {new Position(1, 1), new Position(0, 1), new Position(0, 0), new Position(0, -1)},
        {new Position(1, -1), new Position(1, 0), new Position(0, 0), new Position(-1, 0)},
    };

    public TetrisJBlock(Position spawnPosition){
        super(spawnPosition);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
}
