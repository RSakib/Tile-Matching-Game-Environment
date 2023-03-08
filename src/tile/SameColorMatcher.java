package tile;

public class SameColorMatcher implements IMatcher{
    public boolean isMatcher(Tile t1, Tile t2){
        if(t1 == null || t2 == null)
            return false;
        else if(t1.getColor() == t2.getColor()){
            return true;
        }
        return false;
    }
}