package pixel.maps;

import com.google.gson.Gson;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class LevelFile
{
	private String tilesetImageFile;
	private int tileSize;
	private int[][] tileLayer;
	private List<ObjectDef> objectLayer;

	public LevelFile ()
	{
	}

	public static LevelFile load (String path) throws Exception
	{
		try (InputStream stream = LevelFile.class.getResourceAsStream (path))
		{
			InputStreamReader reader = new InputStreamReader (stream);
			Gson gson = new Gson ();
			LevelFile level = gson.fromJson (reader, LevelFile.class);
			return level;
		}
	}

    public TileMap getTileMap () throws Exception
    {
        Tileset tileset = createTileset ();
		List<MapObject> mapObjects = createObjectLayer ();
        TileMap tileMap = new TileMap (tileset, tileLayer);
        return tileMap;
    }

	private List<MapObject> createObjectLayer ()
	{
		List<MapObject> list = new ArrayList<> ();

		if (this.objectLayer != null)
		{
			for (ObjectDef def : this.objectLayer)
			{
				list.add (def.getMapObject ());
			}
		}

		return list;
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

	public int[][] getTileLayer ()
	{
		return tileLayer;
	}

	public void setTileLayer (int[][] value)
	{
		this.tileLayer = value;
	}

	public List<ObjectDef> getObjectLayer ()
	{
		return objectLayer;
	}

	public void setObjectLayer (List<ObjectDef> value)
	{
		this.objectLayer = value;
	}
}
