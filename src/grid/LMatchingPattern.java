package grid;

import java.util.List;

public class LMatchingPattern implements IMatchingPattern {
	private int legLength;
	private IMatchingPattern hzPattern;
	private IMatchingPattern vtPattern;
	
	public LMatchingPattern(int n) {
		legLength = n;
		hzPattern = new HorizontalMatchingPattern(n);
		vtPattern = new VerticalMatchingPattern(n);
	}
	
	@Override
	public List<Position> findMatch(Grid grid, int startRow, int startCol) {
		List<Position> matches = hzPattern.findMatch(grid, startRow, startCol);
		
		if (matches.size() == 0) {
			return matches;
		}
		
		
		// TODO Check to make sure vertical pattern start row/col is valid for the grid
		List<Position> leftUp = vtPattern.findMatch(grid, startRow + legLength - 1, startCol);
		List<Position> leftDown = vtPattern.findMatch(grid, startRow, startCol);
		List<Position> rightUp = vtPattern.findMatch(grid, startRow + legLength - 1, startCol + legLength - 1);
		List<Position> rightDown = vtPattern.findMatch(grid, startRow, startCol + legLength - 1);
		if (leftUp.size() != 0) {
			matches.remove(0);
			matches.addAll(leftUp);
			return matches;
		} 
		if (leftDown.size() != 0) {
			matches.remove(0);
			matches.addAll(leftDown);
			return matches;
		} 
		if (rightUp.size() != 0) {
			matches.remove(legLength - 1);
			matches.addAll(rightUp);
			return matches;
		}
		if (rightDown.size() != 0) {
			matches.remove(legLength - 1);
			matches.addAll(rightDown);
			return matches;
		}
		
		matches.clear();
		return matches;	
	}

}
