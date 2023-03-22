package tile.matchers;

import tile.Color;
import tile.Tile;

public class SameColorMatcher implements IMatcher{
    public boolean isMatch(Tile t1, Tile t2)
    {
        if(t1 == null || t2 == null)
            return false;
        else if(t1.getColor() == t2.getColor() && t1.getColor() != Color.EMPTY){
            return true;
        }
        return false;
    }
}