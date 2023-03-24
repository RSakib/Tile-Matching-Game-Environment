package tile.BejeweledTiles;

import tile.TileColor;
import tile.exploders.SelfExplode;

public class HypercubeTile extends BejeweledTile{
    public HypercubeTile()
    {
        super(TileColor.MULTICOLOR);
        setExploder(new SelfExplode()); // will set tile that matched with this to same color exploder
    }
}
