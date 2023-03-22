package tmGame;

import java.util.Set;

import grid.Direction;
import grid.Grid;
import grid.IMatchingPattern;
import grid.Position;
import grid.IFallableBlocks.IFallable;
import grid.IGravity.IGravity;
import javafx.geometry.Pos;
import tile.EmptyTile;
import tmGame.gameOverConditions.GameOverCondition;
import tmGame.gameScreen.GameScreenJFX;

public abstract class FallingBlockGame extends TileMatchingGame{
    private IFallable currentFaller;

    public FallingBlockGame() {
        super();
        currentFaller = null;
    }


    public IFallable getCurrentFaller() {
        return currentFaller;
    }

    public void setCurrentFaller(IFallable faller) {
        this.currentFaller = faller;
    }


    public void shiftFaller(Direction direction) {
        if (canShiftFaller(direction)) {
            currentFaller.shift(direction);
        }
    }


    public void moveFallerDown() {
        if (currentFaller == null) {
            applyGravity();
            if (! matchTiles()) {
                setCurrentFaller(createNewFaller());
            }
        } else if (canShiftFaller(Direction.DOWN)) {
            currentFaller.shift(Direction.DOWN);
        } else {
            addFallerToGrid();
        }
    }


    private boolean canShiftFaller(Direction direction) {
        Set<Position> fallerPositions = currentFaller.getBlock().keySet();

        for (Position p : fallerPositions) {
            Position adjacent = p.translate(direction);
            if (!grid.validPosition(adjacent) || !grid.tileAt(adjacent).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void addFallerToGrid() {
        currentFaller.getBlock().forEach((pos, tile) -> grid.setTile(pos, tile));
        currentFaller = null;
    }

    public abstract IFallable createNewFaller();
    public abstract void rotateFaller();
    
}
