package tile.BejeweledTiles;

import tile.Color;
import tile.IExploder;
import tile.SelfExplode;
import tile.Tile;

public class BejeweledTile extends Tile{
    private static IExploder exploder = new SelfExplode();

    public BejeweledTile(Color color)
    {
        setColor(color);
        setExploder(exploder); //sets exploder to self explode
    }
}
