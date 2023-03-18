package grid.IFallableBlocks;
import java.util.ArrayList;

import grid.Direction;
import grid.IFallable;
import grid.Position;
import tile.Tile;



public class TetrisReverseLBlock extends IFallable{
    public Direction direction;

    public TetrisReverseLBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
        direction = Direction.UP;
    }
    public void rotate(){
        if(direction == Direction.UP){

        }
    }
}