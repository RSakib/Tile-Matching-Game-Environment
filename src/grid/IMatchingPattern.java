package grid;

import java.util.List;

public interface IMatchingPattern {
	public List<Position> findMatch(Grid grid, int startRow, int startCol);
}
