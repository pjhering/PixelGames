package pixel.maps;

import java.awt.image.BufferedImage;

public class Tileset
{
	private Tile[] tiles;

	public Tileset (BufferedImage image, int tileSize)
	{
	}

	public Tile getTile(int i)
	{
		return tiles[i];
	}
}
