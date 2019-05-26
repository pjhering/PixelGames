package pixel.maps;

import java.awt.Graphics;

public class TileMap
{
    private Tileset tileset;
	private int[][] tileLayer;

    public TileMap (Tileset ts, int[][] tl)
    {
        this.tileset = ts;
		this.tileLayer = tl;
    }

	public void draw (Graphics g)
	{
		int size = tileset.getTileSize ();

		for (int r = 0; r < tileLayer.length; r++)
		{
			int y1 = r * size;
			int y2 = y1 + size;

			for (int c = 0; c < tileLayer[r].length; c++)
			{
				int x1 = c * size;
				int x2 = x1 + size;
				int i = tileLayer[r][c];

				tileset.getTile (i).draw (g, x1, y1, x2, y2);
			}
		}
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
