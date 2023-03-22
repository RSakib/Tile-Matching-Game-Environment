package tile.BejeweledTiles;

import tile.Color;
import tile.exploders.SquareExplode;

public class SquareTilePowerUp extends BejeweledTile{
    public SquareTilePowerUp(Color color)
    {
        super(color);
        setExploder(new SquareExplode());
    }
}
