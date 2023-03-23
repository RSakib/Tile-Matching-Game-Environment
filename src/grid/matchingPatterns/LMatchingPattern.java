package grid.matchingPatterns;

import java.util.ArrayList;
import java.util.List;

import grid.Grid;
import grid.Position;

public class LMatchingPattern implements IMatchingPattern {
	private int legLength;
	private HorizontalMatchingPattern hzPattern;
	private VerticalMatchingPattern vtPattern;
	
	public LMatchingPattern(int n) {
		legLength = n;
		hzPattern = new HorizontalMatchingPattern(n);
		vtPattern = new VerticalMatchingPattern(n);
	}
	
	@Override
	public Match findMatch(Grid grid, Position position) {
		Match hzMatch = hzPattern.findMatch(grid, position);
		if (hzMatch.isMatch()) {
			Position left = hzMatch.getPositions().get(0);
			Position right = hzMatch.getPositions().get(hzMatch.getNumMatched() - 1);

			// find match starting/ending at two ends of hzMatch.matchedPositions
			List<Match> matches = new ArrayList<>();
			Match leftUp = vtPattern.matchStartsAt(grid, new Position(left.row - (legLength - 1), left.col));
			Match leftDown = vtPattern.matchStartsAt(grid, new Position(left.row, left.col));
			Match rightUp = vtPattern.matchStartsAt(grid, new Position(right.row - (legLength - 1), right.col));
			Match rightDown = vtPattern.matchStartsAt(grid, new Position(right.row, right.col));
			matches.add(leftUp);
			matches.add(leftDown);
			matches.add(rightUp);
			matches.add(rightDown);

			for (Match m : matches) {
				if (m.isMatch()) {
					List<Position> allPositions = hzMatch.getPositions();
					allPositions.addAll(m.getPositions());
					return new Match(this, allPositions);
				}
			}
			return new NoMatch();
		}

		Match vtMatch = vtPattern.findMatch(grid, position);
		if (! (vtMatch instanceof NoMatch)) {
			Position top = vtMatch.getPositions().get(0);
			Position bot = vtMatch.getPositions().get(vtMatch.getNumMatched() - 1);

			// find match starting/ending at two ends of vtMatch.matchedPositions	
			List<Match> matches = new ArrayList<>();
			Match topLeft = hzPattern.matchStartsAt(grid, new Position(top.row, top.col - (legLength - 1)));
			Match topRight = hzPattern.matchStartsAt(grid, new Position(top.row, top.col));
			Match botLeft = hzPattern.matchStartsAt(grid, new Position(bot.row, bot.col - (legLength - 1)));
			Match botRight = hzPattern.matchStartsAt(grid, new Position(bot.row, bot.col));
			matches.add(topLeft);
			matches.add(topRight);
			matches.add(botLeft);
			matches.add(botRight);

			for (Match m : matches) {
				if (! (m instanceof NoMatch)) {
					List<Position> allPositions = vtMatch.getPositions();
					allPositions.addAll(m.getPositions());
					return new Match(this, allPositions);
				}
			}
			return new NoMatch();
		}

		return new NoMatch();
	}

}
