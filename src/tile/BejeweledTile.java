package tile;

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
