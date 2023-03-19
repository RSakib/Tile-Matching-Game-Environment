package tile;

public class BejeweledTile extends Tile{
    private Color color;
    BejeweledTile(Color color)
    {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
