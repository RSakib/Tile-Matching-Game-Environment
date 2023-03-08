package grid;

import java.util.List;

public interface IMatchingPattern {
	/**
	 * Checks whether the given position is a part of a match on the grid
	 * All possibilities/orientations of the matching pattern are checked.
	 * @param grid
	 * @param position
	 * @return A Match object describing the match, returns NoMatch() if no match found
	 */
	public Match findMatch(Grid grid, Position position);
}
