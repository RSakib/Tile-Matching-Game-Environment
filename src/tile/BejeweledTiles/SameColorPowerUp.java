package tile.BejeweledTiles;

import tile.Color;
import tile.SameColorExplode;

public class SameColorPowerUp extends BejeweledTile{
    public SameColorPowerUp(Color color)
    {
        super(color);
        setExploder(new SameColorExplode());
    }
}
