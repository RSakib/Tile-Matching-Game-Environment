package grid.matchingPatterns;

import java.util.ArrayList;
import java.util.List;

import grid.Grid;
import grid.Position;
import tile.Tile;

public class VerticalMatchingPattern implements IMatchingPattern{
	private int numMatching;
	
	public VerticalMatchingPattern(int n) {
		numMatching = n;
	}
	

	@Override
	public Match findMatch(Grid grid, Position position) {
		for (int rowOffset = 0; rowOffset > -numMatching; rowOffset--) {
			Match m = matchStartsAt(grid, new Position(position.row + rowOffset, position.col));
			if (m.isMatch()) {
				return m;
			}
		}
		return new NoMatch();
	}


	public Match matchStartsAt(Grid grid, Position startPosition) {
		if (! grid.validPosition(startPosition)) {
			return new NoMatch();
		}

		Tile startTile = grid.tileAt(startPosition);
		List<Position> matches = new ArrayList<>();
		for (int i = 0; i < numMatching; i++) {
			int row = startPosition.row + i;
			int col = startPosition.col;
			Position p = new Position(row, col);
			
			if (!grid.validPosition(p) || !startTile.isMatch(grid.tileAt(p))) {
				return new NoMatch();
			}
			matches.add(p);
		}
		return new Match(this, matches);
	}
}
