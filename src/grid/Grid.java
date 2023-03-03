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
	
	
	public Tile tileAt(int row, int col) {
		// if not valid position throw exception
		
		return grid[row][col];
	}
	
	
	public boolean matchAt(Position p1, Position p2) {
		if (!validPosition(p1.row, p1.col) || !validPosition(p2.row, p2.col)) {
			return false;
		}
		
//		return matcher.isMatch(grid[p1.row][p1.col], grid[p2.row][p2.col]);
		return false;
	}
	
	
	
	public abstract boolean validPosition(int row, int col);
	public abstract int matchTiles();
	public abstract void applyGravity();
}
