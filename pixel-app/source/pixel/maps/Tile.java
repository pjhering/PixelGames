package pixel.maps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile
{
	private final BufferedImage image;
	private final int imageX1, imageY1, imageX2, imageY2;
	private final boolean blocked;

	public Tile (BufferedImage image, int x1, int y1, int x2, int y2, boolean blocked)
	{
		this.image = image;
		this.imageX1 = x1;
		this.imageY1 = y1;
		this.imageX2 = x2;
		this.imageY2 = y2;
		this.blocked = blocked;
	}

	public void draw (Graphics g, int x1, int y1, int x2, int y2)
	{
		g.drawImage (image, x1, y1, x2, y2, imageX1, imageY1, imageX2, imageY2, null);
	}

	public boolean isBlocked ()
	{
		return blocked;
	}
}
