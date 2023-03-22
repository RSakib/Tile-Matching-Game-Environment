package tile.BejeweledTiles;

import tile.Color;
import tile.SameColorExplode;

public class SameColorPowerUp extends BejeweledTile{
    public SameColorPowerUp()
    {
        super(Color.MULTICOLOR);
        setExploder(new SameColorExplode());
    }
}
