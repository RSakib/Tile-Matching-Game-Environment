package grid;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;

import tile.Tile;

public abstract class IFallable {
    public ArrayList<Position> blockPositions;
    public Position spawnPosition;
    public Tile tileType;
    public boolean isFrozen;

    public IFallable(Position spawnPosition, Tile tileType){
        this.spawnPosition = spawnPosition;
        this.tileType = tileType;
        this.isFrozen = false;
        this.blockPositions = new ArrayList<Position>(4);
    }

    public abstract void rotate();

    public void shift(Direction direction){
       for (Position blockPosition : blockPositions){
        if (direction == Direction.LEFT){
            blockPosition.col -= 1;
        }else if (direction == Direction.RIGHT){
            blockPosition.col += 1;
        }else{}
       } 
    }
    public void fall(){
        for (Position blockPosition : blockPositions){
            blockPosition.row -= 1;
        }
    };

    public boolean isFrozen(){
        return isFrozen;
    }
    public void freeze(){
        this.isFrozen = true;
    };
    public HashMap<Position, Tile> getBlock(){
        HashMap positionMap = new HashMap<Position, Tile>();
        for (Position blockPosition : blockPositions){
            positionMap.put(blockPosition, tileType);
        } 
        return positionMap;
    };
}
