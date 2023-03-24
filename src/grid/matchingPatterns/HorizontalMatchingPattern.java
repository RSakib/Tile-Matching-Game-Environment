package grid.matchingPatterns;

import java.util.ArrayList;
import java.util.List;

import grid.Grid;
import grid.Position;
import tile.Tile;

public class HorizontalMatchingPattern implements IMatchingPattern{
	private int numMatching;
	
	public HorizontalMatchingPattern(int n) {
		numMatching = n;
	}


	@Override
	public Match findMatch(Grid grid, Position position) {
		for (int colOffset = 0; colOffset > -numMatching; colOffset--) {
			Match m = matchStartsAt(grid, new Position(position.row, position.col + colOffset));
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
			int row = startPosition.row;
			int col = startPosition.col + i;
			Position p = new Position(row, col);
			
			if (!grid.validPosition(p) || !startTile.isMatch(grid.tileAt(p))) {
				return new NoMatch();
			}
			matches.add(p);
		}
		return new Match(this, matches);
	}

	// private boolean sameInRow(Grid grid, Tile tileToMatch, Position startPosition) {
	// 	IMatcher matcher = grid.getMatcher();

	// 	for (int i = 0; i < numMatching; i++) {
	// 		int row = startPosition.row;
	// 		int col = startPosition.col + i;
	// 		Position pos = new Position(row, col);

	// 		if (!grid.validPosition(pos)) || !matcher.isMatch(grid.tileAt(pos)) {
	// 			return false;
	// 		}
	// 	}
	// 	return true;
	// }
	
}
