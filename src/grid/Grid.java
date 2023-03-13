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
	public abstract int matchTiles();
	public abstract void applyGravity();
}
