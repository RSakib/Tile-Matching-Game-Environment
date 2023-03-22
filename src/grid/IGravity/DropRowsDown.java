package grid.IGravity;

import grid.Grid;

public class DropRowsDown implements IGravity {

    @Override
    public void applyGravity(Grid grid) {
        for (int row = grid.getNumRows() - 1; row >= 0; row--) {
            if (grid.isEmptyRow(row)) {
                int nextNonEmptyRow = grid.nonEmptyRowAbove(row);
                if (nextNonEmptyRow == -1) {
                    return;
                }

                grid.swapRows(row, nextNonEmptyRow);
            }
        }
    }
    
}
