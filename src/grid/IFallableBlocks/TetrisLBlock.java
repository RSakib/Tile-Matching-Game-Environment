package grid.IFallableBlocks;
import grid.Position;
import tile.TileColor;


public class TetrisLBlock extends TetrisBlock{
    private static Position[][] rotationPositions = {
        {new Position(0, -1), new Position(0, 0), new Position(0, 1), new Position(-1, 1)},
        {new Position(-1, 0), new Position(0, 0), new Position(1, 0), new Position(1, 1)},
        {new Position(0, 1), new Position(0, 0), new Position(0, -1), new Position(1, -1)},
        {new Position(1, 0), new Position(0, 0), new Position(-1, 0), new Position(-1, -1)},
    };

    public TetrisLBlock(Position spawnPosition){
        super(spawnPosition, TileColor.ORANGE);
    }

    @Override
    public Position[][] rotationPositions() {
        return rotationPositions;
    }
}