package grid;

import java.util.ArrayList;
import java.util.List;

import tile.Tile;

public class VerticalMatchingPattern implements IMatchingPattern{
	private int numMatching;
	
	public VerticalMatchingPattern(int n) {
		numMatching = n;
	}
	

	@Override
	public List<Position> findMatch(Grid grid, int startRow, int startCol) {
		List<Position> matchedPositions = new ArrayList<Position>();
		
		Tile startTile = grid.tileAt(startRow, startCol);
		for (int i = 1; i < numMatching; i++) {
			int row = startRow + i;
			int col = startCol;
			// check if (row, col) is valid grid position, if not clear positions and return

			Tile t = grid.tileAt(row, col);
			// If grid.getMatcher.isMatch(startTile, t) add row, col to positions list
			// else clear positions list and return
		}
		
		return matchedPositions;
	}
	
}
