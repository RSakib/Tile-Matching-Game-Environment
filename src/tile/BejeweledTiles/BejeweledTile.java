package tile.BejeweledTiles;

import tile.Color;
import tile.Tile;
import tile.exploders.IExploder;
import tile.exploders.SelfExplode;
import tile.matchers.SameColorMatcher;

public class BejeweledTile extends Tile{
    public BejeweledTile(Color color)
    {
        setColor(color);
        setMatcher(new SameColorMatcher());
        setExploder(new SelfExplode()); //sets exploder to self explode
    }
}
