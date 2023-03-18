package grid.IFallableBlocks;
import tile.Tile;
import java.util.ArrayList;

import grid.Direction;
import grid.IFallable;
import grid.Position;


public class TetrisIBlock extends IFallable{
    public Direction direction;

    public TetrisIBlock(ArrayList<Position> blockPositions, Tile tileType){
        super(blockPositions, tileType);
        direction = Direction.UP;
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
        }else if(direction == Direction.LEFT || direction == Direction.RIGHT){
            Position pos = new Position(axis.row+1, axis.col);
            blockPositions.set(1, pos);
            pos = new Position(axis.row+2, axis.col);
            blockPositions.set(2,pos);
            pos = new Position(axis.row-1, axis.col);
            blockPositions.set(3, pos);
        }
    }
}