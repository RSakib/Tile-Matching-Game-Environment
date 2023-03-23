package tile.BejeweledTiles;

import tile.Color;
import tile.exploders.CrossExplode;

public class StarTile extends BejeweledTile{

    public StarTile(Color color) {
        super(color);
        setExploder(new CrossExplode());
    }
    
}
