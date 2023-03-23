package tile.BejeweledTiles;

import tile.Color;
import tile.exploders.SameColorExplode;
import tile.exploders.SelfExplode;

public class HypercubeTile extends BejeweledTile{
    public HypercubeTile()
    {
        super(Color.MULTICOLOR);
        setExploder(new SelfExplode()); // will set tile that matched with this to same color exploder
    }
}
