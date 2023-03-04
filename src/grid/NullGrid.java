package grid;

import tile.Tile;

public class NullGrid extends Grid {

	public NullGrid(int rows, int cols) {
		super(rows, cols);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validPosition(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tile tileAt(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int matchTiles(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void applyGravity() {
		// TODO Auto-generated method stub

	}

}
