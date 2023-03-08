package tile;

public class NonEmptyMatcher implements IMatcher{
    public boolean isMatcher(Tile t1, Tile t2){
        if(t1 instanceof EmptyTile || t2 instanceof EmptyTile)
            return false;
        return true;
    }
}