package grid.gravity;

import grid.Grid;

/**
 * defines how tiles should fill empty space in a grid
 */
public interface IGravity {
    public void applyGravity(Grid grid);
}
