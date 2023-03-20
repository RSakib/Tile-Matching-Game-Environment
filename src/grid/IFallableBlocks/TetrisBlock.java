package grid.IFallableBlocks;
import java.util.Map;

import grid.Direction;
import grid.Position;
import grid.IFallable;

import java.util.ArrayList;
import java.util.*;

import tile.TetrisTile;
import tile.Tile;

public abstract class TetrisBlock implements IFallable {
    private Position rotationPoint;
    private int rotationIndex;
    private boolean isFrozen;

    public TetrisBlock(Position spawnPosition){
        this.rotationPoint = spawnPosition;
        this.rotationIndex = 0;
        this.isFrozen = false;
    }

    public abstract Position[][] rotationPositions();

    public void rotateClockwise() {
        rotationIndex = (rotationIndex + 1) % rotationPositions().length;
    }

    public void rotateCounterClockwise() {
        // wrap around negative mod in java, if size is 4, -1 -> 3.
        rotationIndex -= 1;
        rotationIndex = (rotationIndex < 0) ? rotationIndex + rotationPositions().length : rotationIndex;
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
            positionMap.put(new Position(rotationPoint.row + p.row, rotationPoint.col + p.col), createTile());
        }
        return positionMap;
    }

    public Tile createTile() {
        return new TetrisTile();
    }
}

