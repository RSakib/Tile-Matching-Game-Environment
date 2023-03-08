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
	public Match findMatch(Grid grid, Position position) {
		Tile startTile = grid.tileAt(position);

		for (int rowOffset = 0; rowOffset > -numMatching; rowOffset--) {
			Match m = matchStartsAt(grid, new Position(position.row + rowOffset, position.col));
			if (! (m instanceof NoMatch)) {
				return m;
			}
		}
		return new NoMatch();
	}


	public Match matchStartsAt(Grid grid, Position startPosition) {
		if (! grid.validPosition(startPosition)) {
			return new NoMatch();
		}

		Tile starTile = grid.tileAt(startPosition);
		// UNCOMMENT WHEN IMATCHER IS IMPLEMENTED
		// IMatcher matcher = grid.getMatcher(); 
		List<Position> matches = new ArrayList<>();
		for (int i = 0; i < numMatching; i++) {
			int row = startPosition.row + i;
			int col = startPosition.col;
			Position p = new Position(row, col);
			
			if (!grid.validPosition(p)) {  //|| !matcher.isMatch(grid.tileAt(p))) {
				return new NoMatch();
			}
			matches.add(p);
		}
		return new Match(this, matches);
	}
}
