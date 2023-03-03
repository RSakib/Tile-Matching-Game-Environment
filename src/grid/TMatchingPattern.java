package grid;

import java.util.List;

public class TMatchingPattern implements IMatchingPattern {
	public int legLength;
	public IMatchingPattern hzPattern;
	public IMatchingPattern vtPattern;
	
	public TMatchingPattern(int n) {
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
		
		int halfLeg = legLength / 2;
		
		// TODO Check to make sure vertical pattern start row/col is valid for the grid
		List<Position> leftMid = vtPattern.findMatch(grid, startRow + halfLeg, startCol);
		List<Position> midUp = vtPattern.findMatch(grid, startRow + legLength - 1, startCol + halfLeg);
		List<Position> midDown = vtPattern.findMatch(grid, startRow, startCol + halfLeg);
		List<Position> rightMid = vtPattern.findMatch(grid, startRow + halfLeg, startCol + legLength - 1);
		
		if (leftMid.size() != 0) {
			matches.remove(0);
			matches.addAll(leftMid);
			return matches;
		} 
		if (midUp.size() != 0) {
			matches.remove(halfLeg);
			matches.addAll(midUp);
			return matches;
		} 
		if (midDown.size() != 0) {
			matches.remove(halfLeg);
			matches.addAll(midDown);
			return matches;
		}
		if (rightMid.size() != 0) {
			matches.remove(legLength - 1);
			matches.addAll(rightMid);
			return matches;
		}
		
		matches.clear();
		return matches;	
	}

}
