package tmGame;

import java.util.Set;

import grid.Direction;
import grid.Grid;
import grid.Position;
import grid.IFallableBlocks.IFallable;
import grid.gravity.DropRowsDown;
import grid.gravity.IGravity;
import grid.matchingPatterns.IMatchingPattern;
import javafx.geometry.Pos;
import tile.EmptyTile;
import tile.Tile;
import tmGame.InputHandler.FallingBlockInputHandler;
import tmGame.InputHandler.InputHandlerJFX;
import tmGame.gameOverConditions.GameOverCondition;
import tmGame.gameOverConditions.GridOverflowed;
import tmGame.gameScreen.GameScreenJFX;
import tmGame.gameScreen.TetrisGameScreen;

public abstract class FallingBlockGame extends TileMatchingGame{
    private IFallable currentFaller;

    @Override
	public void initializeGame(		
		Grid grid, GameScreenJFX screen, InputHandlerJFX input, 
		GameOverCondition gameOver, IMatchingPattern[] matchingPatterns, IGravity gravity, double secondsPerTick) 
	{
		super.initializeGame(grid, screen, input, gameOver, matchingPatterns, gravity, secondsPerTick);
        this.currentFaller = null;
	}


    public IFallable getCurrentFaller() {
        return currentFaller;
    }

    public void setCurrentFaller(IFallable faller) {
        this.currentFaller = faller;
    }


    @Override
    public Tile visibleTileAt(Position p) {
        if (currentFaller != null) {
            Tile tile = currentFaller.getBlock().get(p);
            return (tile != null) ? tile : super.visibleTileAt(p);
        }
        return super.visibleTileAt(p);
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
            matchTiles();
        }
    }


    private boolean canShiftFaller(Direction direction) {
        if (currentFaller == null) { 
            return false; 
        }
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
