package run2right;

import java.awt.Graphics;
import pixel.maps.MapObject;

public abstract class Actor
{
	protected final MapObject object;

	public Actor (MapObject obj)
	{
		this.object = obj;
	}

	public abstract boolean update (long elapsed);

	public abstract void draw (Graphics g, int xOffset, int yOffset);

	public MapObject getMapObject ()
	{
		return object;
	}
}
