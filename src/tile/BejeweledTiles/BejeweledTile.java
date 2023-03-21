package tile.BejeweledTiles;

import tile.Color;
import tile.SelfExplode;
import tile.Tile;

public class BejeweledTile extends Tile{
    public BejeweledTile(Color color)
    {
        setColor(color);
        setExploder(new SelfExplode()); //sets exploder to self explode
    }
}
