package grid.IFallableBlocks;
import java.util.ArrayList;

import grid.Direction;
import grid.IFallable;
import grid.Position;
import tile.Tile;



public class TetrisZBlock extends IFallable{
    public Direction direction;

    public TetrisZBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
        direction = Direction.UP;
    }
    public void rotate(){
        if(direction == Direction.UP){
            
        }
    }
}