package grid;

import java.util.ArrayList;
import java.util.List;

import tile.EmptyTile;
import tile.IMatcher;
import tile.Tile;

public abstract class Grid {
	protected int score;
	private int numRows;
	private int numColumns;
	private IMatcher matcher;
	private IMatchingPattern[] matchingPatterns;
	private Tile[][] grid;
	
	public Grid(int rows, int cols, IMatcher matcher, IMatchingPattern[] patterns){
		this.numRows = rows;
		this.numColumns = cols;
		this.matcher = matcher;
		this.matchingPatterns = patterns;
		grid = new Tile[rows][cols];
		// initialize to empty tiles
		initializeGrid();
	}
	
	public final int getNumRows() {
		return numRows;
	}
	
	public final int getNumCols() {
		return numColumns;
	}


	public final IMatcher getMatcher() {
		return matcher;
	}

	public final int getScore() {
		return score;
	}
	
	
	public Tile tileAt(Position p) {
		// if not valid position throw exception
		
		return grid[p.row][p.col];
	}


	public void setTile(Position p, Tile t){
		// assert valid position

		grid[p.row][p.col] = t;
	}
	
	
	public Match matchAt(Position p1) 
	{
		//Loop through all set of matching patterns
		for (IMatchingPattern pattern: matchingPatterns)
		{
			Match matchedPattern = pattern.findMatch(this, p1);
			//if there is at least one match, return true
			if(!(matchedPattern instanceof NoMatch))
			{
				//Using instanceof to check, there might be a better way -- waiting for response from team
				return matchedPattern; //stub, need to reference the design pattern specifically
			}
		}
		//Use for-each loop to loop through the set (look up online)
			//Check if that position is a match given the matching pattern
		return new NoMatch(); //returns no match if no match
	}


	/**
	 * Recursively computes a list of positions that should be exploded by starting an explosion at the given position
	 * @param p
	 * @return
	 */
	public List<Position> getExplodedTiles(Position p) {
		Tile tile = tileAt(p);
		if (tile.isExploded()) {
			return new ArrayList<>();
		}

		List<Position> explodedPositions = tile.explode(this, p);
		for (Position explodedPos: explodedPositions) {
			Tile explodedTile = tileAt(explodedPos);
			if (! explodedPos.equals(p)) {
				explodedPositions.addAll(getExplodedTiles(explodedPos));
			}
			explodedTile.setExploded(true);
		}
		return explodedPositions;
	}
	
	
	/**
	 * Returns whether or not the position on the grid is valid
	 * @param p The position to test
	 * @return boolean
	 */
	public boolean validPosition(Position p)
	{
		//Checks if position is within the array bounds
		if(p.row >= 0 && p.row < numRows && p.col >= 0 && p.col < numColumns)
		{
			//If valid row and column, return true
			return true;
		}
		//Else, return false
		return false;
	}
	
	public void initializeGrid() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				setTile(new Position(i, j),new EmptyTile());
			}
		}
	}


	public String toString() {
		String str = "";
		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumCols(); c++) {
				if (tileAt(new Position(r, c)) instanceof EmptyTile) {
					str += "0 ";
				} else {
					str += "1 ";
				}
			}
			str += "\n";
		}
		return str;
	}

	public abstract int matchTiles();
	public abstract void applyGravity();
}
