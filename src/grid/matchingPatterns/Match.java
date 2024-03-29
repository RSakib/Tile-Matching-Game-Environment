package grid.matchingPatterns;

import java.util.List;

import grid.Position;

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

    public boolean isMatch() {
        return ! (this instanceof NoMatch);
    }
}
