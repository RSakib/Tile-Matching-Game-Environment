package grid.IFallableBlocks;
import java.util.Map;

import grid.Direction;
import grid.Position;
import grid.IFallable;

import java.util.ArrayList;
import java.util.*;

import tile.Tile;

public abstract class TetrisBlock implements IFallable {
    private Position rotationPoint;
    private int rotationIndex;
    private Tile tileType;
    private boolean isFrozen;

    public TetrisBlock(Position spawnPosition, Tile tileType){
        this.rotationPoint = spawnPosition;
        this.rotationIndex = 0;
        this.tileType = tileType;
        this.isFrozen = false;
    }

    public abstract Position[][] rotationPositions();

    public void rotateClockwise() {
        rotationIndex = (rotationIndex + 1) % rotationPositions().length;
    }

    public void rotateCounterClockwise() {
        rotationIndex = (rotationIndex - 1) % rotationPositions().length;
    }

    public void shift(Direction direction){
        rotationPoint.move(direction);
    }

    public void fall(){
        rotationPoint.move(Direction.DOWN);
    }

    public boolean isFrozen(){
        return isFrozen;
    }

    public void freeze(){
        this.isFrozen = true;
    }

    public Map<Position, Tile> getBlock(){
        Map<Position, Tile> positionMap = new HashMap<Position, Tile>();
        for (Position p : rotationPositions()[rotationIndex]){
            positionMap.put(new Position(rotationPoint.row + p.row, rotationPoint.col + p.col), tileType);
        }
        return positionMap;
    }
}

