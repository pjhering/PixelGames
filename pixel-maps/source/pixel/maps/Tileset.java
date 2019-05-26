package pixel.maps;

import java.awt.image.BufferedImage;

public class Tileset
{
	private Tile[] tiles;
	private int tileSize;

	public Tileset (BufferedImage image, int tileSize)
	{
		this.tileSize = tileSize;

		int rows = image.getHeight () / tileSize;
		int cols = image.getWidth () / tileSize;
		int count = rows * cols;
		int halfWay = rows / 2;
		tiles = new Tile[count];
		int i = 0;

		for (int r = 0; r < rows; r++)
		{
			boolean blocked = r >= halfWay;
			int y1 = r * tileSize;
			int y2 = y1 + tileSize;

			for (int c = 0; c < cols; c++)
			{
				int x1 = c * tileSize;
				int x2 = x1 + tileSize;

				tiles[i] = new Tile (image, x1, y1, x2, y2, blocked);
				i++;
			}
		}
	}

	public Tile getTile(int i)
	{
		return tiles[i];
	}

	public int getTileSize ()
	{
		return tileSize;
	}
}
