package tile.BejeweledTiles;

import tile.TileColor;
import tile.exploders.SquareExplode;

public class FlameTile extends BejeweledTile{
    public FlameTile(TileColor color)
    {
        super(color);
        setExploder(new SquareExplode());
    }
}
