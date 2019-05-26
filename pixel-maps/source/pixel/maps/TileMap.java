package pixel.maps;

public class TileMap
{
    private Tileset tileset;

    public TileMap (Tileset ts)
    {
        this.tileset = ts;
    }

    public Tileset getTileset ()
    {
        return tileset;
    }
}
