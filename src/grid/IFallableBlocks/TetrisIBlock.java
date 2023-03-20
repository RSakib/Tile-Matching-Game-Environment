package grid.IFallableBlocks;
import tile.Tile;
import java.util.ArrayList;

import grid.Direction;
import grid.IFallable;
import grid.Position;


public class TetrisIBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(0, -1), new Position(0, 0), new Position(0, 1), new Position(0, 2)},
        {new Position(-1, 1), new Position(0, 1), new Position(1, 1), new Position(2, 1)},
        {new Position(1, -1), new Position(1, 0), new Position(1, 1), new Position(1, 2)},
        {new Position(-1, 0), new Position(0, 0), new Position(1, 0), new Position(2, 0)},
    };

    public TetrisIBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
}