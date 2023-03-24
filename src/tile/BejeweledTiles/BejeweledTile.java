package tile.BejeweledTiles;

import tile.TileColor;
import tile.Tile;
import tile.exploders.SelfExplode;
import tile.matchers.SameColorMatcher;

public class BejeweledTile extends Tile{
    public BejeweledTile(TileColor color)
    {
        setColor(color);
        setMatcher(new SameColorMatcher());
        setExploder(new SelfExplode()); //sets exploder to self explode
    }
}
