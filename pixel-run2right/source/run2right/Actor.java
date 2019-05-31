package run2right;

import java.awt.Graphics;
import pixel.Assets;
import pixel.maps.MapObject;

public abstract class Actor
{
	protected final MapObject object;
    protected final Assets a;

	public Actor (MapObject obj, Assets a)
	{
		this.object = obj;
        this.a = a;
	}

	public abstract boolean update (long elapsed);

	public abstract void draw (Graphics g, int xOffset, int yOffset);

	public MapObject getMapObject ()
	{
		return object;
	}
}
