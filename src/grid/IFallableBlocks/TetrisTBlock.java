package grid.IFallableBlocks;
import tile.Tile;
import java.util.ArrayList;

import grid.Direction;
import grid.IFallable;
import grid.Position;


public class TetrisTBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(0, -1), new Position(0, 0), new Position(-1, 0), new Position(0, 1)},
        {new Position(-1, 0), new Position(0, 0), new Position(0, 1), new Position(1, 0)},
        {new Position(0, 1), new Position(0, 0), new Position(1, 0), new Position(0, -1)},
        {new Position(1, 0), new Position(0, 0), new Position(0, -1), new Position(-1, 0)},
    };

    public TetrisTBlock(Position spawnPosition){
        super(spawnPosition);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
}