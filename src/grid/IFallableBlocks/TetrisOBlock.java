package grid.IFallableBlocks;

import grid.Position;
import tile.TileColor;

public class TetrisOBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(0, 0), new Position(0, 1), new Position(-1, 0), new Position(-1, 1)}
    };

    public TetrisOBlock(Position spawnPosition){
        super(spawnPosition, TileColor.YELLOW);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
    

}
