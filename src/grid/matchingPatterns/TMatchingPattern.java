package grid.matchingPatterns;

import java.util.ArrayList;
import java.util.List;

import grid.Grid;
import grid.Position;

public class TMatchingPattern implements IMatchingPattern {
	public int legLength;
	public HorizontalMatchingPattern hzPattern;
	public VerticalMatchingPattern vtPattern;
	
	public TMatchingPattern(int n) {
		legLength = n;
		hzPattern = new HorizontalMatchingPattern(n);
		vtPattern = new VerticalMatchingPattern(n);
	}


	@Override
	public Match findMatch(Grid grid, Position position) {
		Match hzMatch = hzPattern.findMatch(grid, position);
		if (! (hzMatch instanceof NoMatch)) {
			Position mid = hzMatch.getPositions().get(hzMatch.getNumMatched() / 2);

			// find match starting/ending at two ends of hzMatch.matchedPositions
			List<Match> matches = new ArrayList<>();
			Match midUp = vtPattern.matchStartsAt(grid, new Position(mid.row - (legLength - 1), mid.col));
			Match midDown = vtPattern.matchStartsAt(grid, new Position(mid.row, mid.col));
			matches.add(midUp);
			matches.add(midDown);

			for (Match m : matches) {
				if (! (m instanceof NoMatch)) {
					List<Position> allPositions = hzMatch.getPositions();
					allPositions.addAll(m.getPositions());
					return new Match(this, allPositions);
				}
			}
		}

		Match vtMatch = vtPattern.findMatch(grid, position);
		if (! (vtMatch instanceof NoMatch)) {
			Position mid = vtMatch.getPositions().get(vtMatch.getNumMatched() / 2);

			// find match starting/ending at two ends of vtMatch.matchedPositions	
			List<Match> matches = new ArrayList<>();
			Match midLeft = hzPattern.matchStartsAt(grid, new Position(mid.row, mid.col - (legLength - 1)));
			Match midRight = hzPattern.matchStartsAt(grid, new Position(mid.row, mid.col));
			matches.add(midLeft);
			matches.add(midRight);

			for (Match m : matches) {
				if (! (m instanceof NoMatch)) {
					List<Position> allPositions = vtMatch.getPositions();
					allPositions.addAll(m.getPositions());
					return new Match(this, allPositions);
				}
			}
		}

		return new NoMatch();
	}

}
