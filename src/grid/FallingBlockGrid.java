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

    public void setCurrentFaller(IFallable faller) {
        currentFaller = faller;
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
                System.out.println("Freeze the block");
                currentFaller.freeze();
                int scoredelta = matchTiles();
                if(scoredelta == 0){
                    score += scoredelta;
                    addFallerToGrid(currentFaller);
                }
                IFallable newFaller = createFaller();
                if (newFaller != null) {
                    setCurrentFaller(newFaller);
                }

                return;
            }
        }

        currentFaller.fall();
    }


    public void addFallerToGrid(IFallable faller) {
        faller.getBlock().forEach((pos, tile) -> setTile(pos, tile));
    }

    public abstract IFallable createFaller();

    public abstract void rotateFaller();

    public abstract void shiftFaller(Direction direction);


}
