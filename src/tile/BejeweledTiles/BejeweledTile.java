package tile.BejeweledTiles;

import tile.Color;
import tile.SelfExplode;
import tile.Tile;

public class BejeweledTile extends Tile{
    private Color color;
    public BejeweledTile(Color color)
    {
        this.color = color;
        setExploder(new SelfExplode()); //sets exploder to self explode
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
