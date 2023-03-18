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
        Position axis = blockPositions.get(0);
        if(direction == Direction.UP){
            Position pos = new Position(axis.row, axis.col-1);
            blockPositions.set(1, pos);
            pos = new Position(axis.row+1, axis.col-1);
            blockPositions.set(2,pos);
            pos = new Position(axis.row, axis.col+1);
            blockPositions.set(3, pos);
            direction = Direction.RIGHT;
        }else if(direction == Direction.RIGHT){
            Position pos = new Position(axis.row-1, axis.col);
            blockPositions.set(1, pos);
            pos = new Position(axis.row-1, axis.col-1);
            blockPositions.set(2,pos);
            pos = new Position(axis.row+1, axis.col);
            blockPositions.set(3, pos);
            direction = Direction.DOWN;
        }else if(direction == Direction.DOWN){
            Position pos = new Position(axis.row, axis.col+1);
            blockPositions.set(1, pos);
            pos = new Position(axis.row-1, axis.col+1);
            blockPositions.set(2,pos);
            pos = new Position(axis.row, axis.col-1);
            blockPositions.set(3, pos);
            direction = Direction.LEFT;
        }else if(direction == Direction.UP){
            Position pos = new Position(axis.row-1, axis.col);
            blockPositions.set(1, pos);
            pos = new Position(axis.row+1, axis.col);
            blockPositions.set(2,pos);
            pos = new Position(axis.row+1, axis.col+1);
            blockPositions.set(3, pos);
            direction = Direction.UP;
        }
    }
}
