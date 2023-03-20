package grid.IFallableBlocks;

import java.util.ArrayList;

import grid.IFallable;
import grid.Position;
import tile.Tile;

public class TetrisJBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(-1, -1), new Position(0, -1), new Position(0, 0), new Position(0, 1)},
        {new Position(-1, 1), new Position(-1, 0), new Position(0, 0), new Position(1, 0)},
        {new Position(1, 1), new Position(0, 1), new Position(0, 0), new Position(0, -1)},
        {new Position(1, -1), new Position(1, 0), new Position(0, 0), new Position(-1, 0)},
    };

    public TetrisJBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
}
