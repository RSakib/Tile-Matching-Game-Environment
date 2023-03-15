package grid;

import tile.IMatcher;

public abstract class FallingBlockGrid extends Grid {



    public FallingBlockGrid(int rows, int cols, IMatcher matcher, IMatchingPattern[] patterns) {
        super(rows, cols, matcher, patterns);
    }

    public abstract IFallable createFaller();

    public abstract void rotateFaller();

    public abstract void shiftFaller(Direction direction);
}
