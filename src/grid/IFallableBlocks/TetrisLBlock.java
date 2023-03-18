package grid.IFallableBlocks;
import java.util.ArrayList;

import grid.Direction;
import grid.IFallable;
import grid.Position;
import tile.Tile;



public class TetrisLBlock extends IFallable{
    public Direction direction;

    public TetrisLBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
        direction = Direction.UP;
        blockPositions.add(spawnPosition);
        blockPositions.add(new Position(spawnPosition.row+1, spawnPosition.col));
        blockPositions.add(new Position(spawnPosition.row+1, spawnPosition.col+1));
        blockPositions.add(new Position(spawnPosition.row-1, spawnPosition.col));
    }
    public void rotate(){
        if(direction == Direction.UP){

        }
    }
}
