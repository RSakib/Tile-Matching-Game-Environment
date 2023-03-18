package grid.IFallableBlocks;
import tile.Tile;
import java.util.ArrayList;

import grid.Direction;
import grid.IFallable;
import grid.Position;


public class TetrisIBlock extends IFallable{
    public Direction direction;

    public TetrisIBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
        direction = Direction.UP;
        blockPositions.add(spawnPosition);
        blockPositions.add(new Position(spawnPosition.row+1, spawnPosition.col));
        blockPositions.add(new Position(spawnPosition.row+2, spawnPosition.col));
        blockPositions.add(new Position(spawnPosition.row-1, spawnPosition.col));
    }
    public void rotate(){
        Position axis = blockPositions.get(0);
        if(direction == Direction.UP || direction == Direction.DOWN){
            Position pos = new Position(axis.row, axis.col-1);
            blockPositions.set(1, pos);
            pos = new Position(axis.row, axis.col-2);
            blockPositions.set(2,pos);
            pos = new Position(axis.row, axis.col+1);
            blockPositions.set(3, pos);
            direction = Direction.RIGHT;
        }else if(direction == Direction.LEFT || direction == Direction.RIGHT){
            Position pos = new Position(axis.row+1, axis.col);
            blockPositions.set(1, pos);
            pos = new Position(axis.row+2, axis.col);
            blockPositions.set(2,pos);
            pos = new Position(axis.row-1, axis.col);
            blockPositions.set(3, pos);
            direction = Direction.UP;
        }
    }
}