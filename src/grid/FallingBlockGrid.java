package grid;

public abstract class FallingBlockGrid extends Grid {

    public FallingBlockGrid(int rows, int cols) {
        super(rows, cols);
    }

    public abstract IFallable createFaller();

    public abstract void rotateFaller();

    public abstract void shiftFaller(Direction direction);
}
