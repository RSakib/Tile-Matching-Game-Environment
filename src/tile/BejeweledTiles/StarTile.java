package tile.BejeweledTiles;

import tile.TileColor;
import tile.exploders.CrossExplode;

public class StarTile extends BejeweledTile{

    public StarTile(TileColor color) {
        super(color);
        setExploder(new CrossExplode());
    }
    
}
