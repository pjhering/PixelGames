package pixel.maps;

import java.awt.Graphics;
import java.util.List;

public class TileMap
{
    private Tileset tileset;
	private int[][] tileLayer;
    private List<MapObject> objectLayer;
    private int mapWidth;
    private int mapHeight;

    public TileMap (Tileset ts, int[][] tl, List<MapObject> ol)
    {
        this.tileset = ts;
		this.tileLayer = tl;
		this.objectLayer = ol;
        this.mapHeight = tl.length * tileset.getTileSize ();
        this.mapWidth = tl[0].length * tileset.getTileSize ();
    }

	public void draw (Graphics g, int xOffset, int yOffset, int startRow, int endRow, int startColumn, int endColumn)
	{
		int size = tileset.getTileSize ();

        for (int r = startRow; r <= endRow; r++)
		{
            if (r >= tileLayer.length) continue;

			int y1 = (r * size) - yOffset;
			int y2 = y1 + size;

            for (int c = startColumn; c <= endColumn; c++)
			{
                if (c >= tileLayer[r].length) continue;

				int x1 = (c * size) - xOffset;
				int x2 = x1 + size;
                int i = tileLayer[r][c];

				tileset.getTile (i).draw (g, x1, y1, x2, y2);
			}
		}
	}

    public Tile getTile (double x, double y)
    {
        int c = (int)(x / tileset.getTileSize ());
        int r = (int)(y / tileset.getTileSize ());
        int i = tileLayer[r][c];
        return tileset.getTile (i);
    }

    public int getColumnForPixel (int x)
    {
        return x / tileset.getTileSize ();
    }

    public int getRowForPixel (int y)
    {
        return y / tileset.getTileSize ();
    }

    public Tileset getTileset ()
    {
        return tileset;
    }

	public int[][] getTileLayer ()
	{
		return tileLayer;
	}

    public List<MapObject> getObjectLayer ()
    {
        return objectLayer;
    }

    public int getMapWidth ()
    {
        return mapWidth;
    }

    public int getMapHeight ()
    {
        return mapHeight;
    }
}
