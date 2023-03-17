package grid.IFallableBlocks;

import java.util.ArrayList;

import grid.IFallable;
import grid.Position;
import tile.Tile;

public class TetrisSquareBlock extends IFallable{

    public TetrisSquareBlock(Position spawnPosition, Tile tileType){
        super(spawnPosition, tileType);
        blockPositions.add(spawnPosition);
        blockPositions.add(new Position(spawnPosition.row, spawnPosition.col+1));
        blockPositions.add(new Position(spawnPosition.row+1, spawnPosition.col));
        blockPositions.add(new Position(spawnPosition.row+1, spawnPosition.col+1));
    }
    
    public void rotate(){

    }
    

}
