package grid;

import java.util.Set;

import tile.EmptyTile;
import tile.IMatcher;
import tile.Tile;

public abstract class FallingBlockGrid extends Grid {
    private IFallable currentFaller;

    public FallingBlockGrid(int rows, int cols, IMatcher matcher, IMatchingPattern[] patterns) {
        super(rows, cols, matcher, patterns);
        currentFaller = null;
    }

    public IFallable getCurrentFaller() {
        return currentFaller;
    }

    @Override
    public Tile tileAt(Position p) {
        if (currentFaller != null){
            Tile tileInFaller = currentFaller.getBlock().get(p);
            return tileInFaller == null ? super.tileAt(p) : tileInFaller;
        }

        return super.tileAt(p);
    }

    public void moveFallerDown() {
        IFallable currentFaller = getCurrentFaller();
        Set<Position> fallerPositions = currentFaller.getBlock().keySet();
        
        for (Position p : fallerPositions) {
            Position below = p.translate(Direction.DOWN);
            if (! fallerPositions.contains(below) && (!validPosition(below) || !(tileAt(below) instanceof EmptyTile))) {
                // faller can't move down
                currentFaller.freeze();
            }
        }

        currentFaller.fall();
    }

    public abstract IFallable createFaller();

    public abstract void rotateFaller();

    public abstract void shiftFaller(Direction direction);
}
