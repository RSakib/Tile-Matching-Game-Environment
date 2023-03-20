package grid.IFallableBlocks;

import java.util.ArrayList;

import grid.IFallable;
import grid.Position;
import tile.Tile;

public class TetrisOBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(0, 0), new Position(0, 1), new Position(-1, 0), new Position(-1, 1)}
    };

    public TetrisOBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
    

}
