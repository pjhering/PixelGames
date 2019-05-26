package pixel.maps;

public class LevelFile
{
	private String tilesetImageFile;
	private int tileSize;

	public LevelFile ()
	{
	}

    public TileMap getTileMap () throws Exception
    {
        Tileset tileset = createTileset ();
        TileMap tileMap = new TileMap (tileset);
        return tileMap;
    }

    private Tileset createTileset () throws Exception
    {
        Class c = getClass ();
        URL url = c.getResource (tilesetImageFile);
        BufferedImage image = ImageIO.read (url);
        Tileset tileset = new Tileset (image, tileSize);
        return tileset;
    }

	public String getTilesetImageFile ()
	{
		return tilesetImageFile;
	}

	public void setTilesetImageFile (String value)
	{
		this.tilesetImageFile = value;
	}

	public int getTileSize ()
	{
		return tileSize;
	}

	public void setTileSize (int value)
	{
		this.tileSize = value;
	}
}
