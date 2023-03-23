package tile.BejeweledTiles;

import tile.Color;
import tile.exploders.SquareExplode;

public class FlameTile extends BejeweledTile{
    public FlameTile(Color color)
    {
        super(color);
        setExploder(new SquareExplode());
    }
}
