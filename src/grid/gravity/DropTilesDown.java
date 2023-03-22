package grid.gravity;

import grid.Grid;
import grid.Position;

public class DropTilesDown implements IGravity{

    @Override
    public void applyGravity(Grid grid) {
        for (int c= 0; c < grid.getNumCols(); c++) {
            for (int r = grid.getNumRows() - 1; r > 0; r--) {
                Position p = new Position(r, c);
                if (grid.tileAt(p).isEmpty()) {
                    Position nonEmptyPos = grid.nonEmptyPositionAbove(p);
                    if (nonEmptyPos == null) {
                        continue;
                    }

                    grid.swapTilesAt(p, nonEmptyPos);
                }
            }
        }
    }
    
}
