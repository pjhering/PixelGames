package pixel.maps;

public class TileMap
{
    private Tileset tileset;
	private int[][] tileLayer;

    public TileMap (Tileset ts, int[][] tl)
    {
        this.tileset = ts;
		this.tileLayer = tl;
    }

    public Tileset getTileset ()
    {
        return tileset;
    }

	public int[][] getTileLayer ()
	{
		return tileLayer;
	}
}
