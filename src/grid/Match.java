package grid;

import java.util.List;

public class Match {
    private IMatchingPattern pattern;
    private List<Position> matchedPositions;


    public Match(IMatchingPattern pattern, List<Position> matchedPositions) {
        this.pattern = pattern;
        this.matchedPositions = matchedPositions;
    }


    public IMatchingPattern getPattern() {
        return this.pattern;
    }

    public List<Position> getPositions() {
        return this.matchedPositions;
    }

    public int getNumMatched() {
        return this.matchedPositions.size();
    }
}
