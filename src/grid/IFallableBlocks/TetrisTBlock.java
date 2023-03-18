package grid.IFallableBlocks;
import java.util.ArrayList;

import grid.Direction;
import grid.IFallable;
import grid.Position;
import tile.Tile;



public class TetrisTBlock extends IFallable{
    public Direction direction;

    public TetrisTBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
        direction = Direction.UP;
    }
    public void rotate(){
        if(direction == Direction.UP){

        }
    }
}