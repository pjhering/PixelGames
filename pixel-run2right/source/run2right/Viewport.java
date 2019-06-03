package run2right;

import pixel.maps.TileMap;

public class Viewport
{
	private final int width;
	private final int height;
	private int xOffset;
	private int yOffset;

	public Viewport (int width, int height)
	{
		this.width = width;
		this.height = height;
		this.xOffset = 0;
		this.yOffset = 0;
	}

	public void update (TileMap map, Actor actor)
	{
		int x = (int) actor.getMapObject ().getLeft ();
		int y = (int) actor.getMapObject ().getTop ();

		xOffset = Math.min (map.getMapWidth () - width, Math.max (0, x - (width / 2)));
		yOffset = Math.min (map.getMapHeight () - height, Math.max (0, y - (height / 2)));
	}

	public int getXOffset ()
	{
		return xOffset;
	}

	public int getYOffset ()
	{
		return yOffset;
	}

	public int getX1 ()
	{
		return xOffset;
	}

	public int getY1 ()
	{
		return yOffset;
	}

	public int getX2 ()
	{
		return xOffset + width;
	}

	public int getY2 ()
	{
		return yOffset + height;
	}
}
