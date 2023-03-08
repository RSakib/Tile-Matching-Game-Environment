package grid;

import java.util.Set;

import tile.Tile;

public abstract class Grid {
	private int numRows;
	private int numColumns;
//	private IMatcher matcher;
	private Set<IMatchingPattern> matchingPatterns;
	private Tile[][] grid;
	
	public Grid(int rows, int cols){
		numRows = rows;
		numColumns = cols;
		grid = new Tile[rows][cols];
		// initialize to empty tiles
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumCols() {
		return numColumns;
	}
	
	
	public Tile tileAt(Position p) {
		// if not valid position throw exception
		
		return grid[p.row][p.col];
	}
	
	
	public boolean matchAt(Position p1, Position p2) {
		return false;
	}
	
	
	
	public abstract boolean validPosition(Position p);
	public abstract int matchTiles();
	public abstract void applyGravity();
}
