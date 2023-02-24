package grid;

import tile.Tile;

public abstract class Grid {
	private int numRows;
	private int numColumns;
	
	
	public Grid(int rows, int cols){
		numRows = rows;
		numColumns = cols;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumCols() {
		return numColumns;
	}
	
	public abstract boolean validPosition(int x, int y);
	public abstract Tile tileAt(int x, int y);
	public abstract int matchTiles(int x, int y);
	public abstract void applyGravity();
}
