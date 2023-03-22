package tile;

public class NeverMatcher implements IMatcher{
    @Override
    public boolean isMatch(Tile t1, Tile t2) {
        return false;
    }
}
