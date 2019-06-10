package run2right;

import java.awt.Graphics;
import pixel.Assets;
import static pixel.Utility.clamp;
import pixel.maps.MapObject;
import pixel.maps.TileMap;

public abstract class Actor
{
	protected final MapObject object;
    protected final Assets a;

	protected double minDeltaX, maxDeltaX;
	protected double minDeltaY, maxDeltaY;
	protected double deltaX, deltaY;
	protected boolean topLeft, topRight;
	protected boolean bottomLeft, bottomRight;
	protected boolean ascending, descending, onGround;

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

	public void move (long elapsedMillis)
	{
		object.setX (object.getX () + (elapsedMillis * deltaX));
		object.setY (object.getY () + (elapsedMillis * deltaY));
	}

	public void updateCornerFlags (TileMap map)
	{
		double top = object.getTop ();
		double bottom = object.getBottom ();
		double left = object.getLeft ();
		double right = object.getRight ();

		topLeft = map.getTile (left, top).isBlocked ();
		bottomLeft = map.getTile (left, bottom).isBlocked ();
		topRight = map.getTile (right, top).isBlocked ();
		bottomRight = map.getTile (right, bottom).isBlocked ();
	}

	public void accelerate (double xAmount, double yAmount)
	{
		deltaX = clamp (deltaX + xAmount, minDeltaX, maxDeltaX);
		deltaY = clamp (deltaY + yAmount, minDeltaY, maxDeltaY);
	}

	public double getMinDeltaX ()
	{
		return minDeltaX;
	}

	public void setMinDeltaX (double value)
	{
		this.minDeltaX = value;
	}

	public double getMinDeltaY ()
	{
		return minDeltaY;
	}

	public void setMinDeltaY (double value)
	{
		this.minDeltaY = value;
	}

	public double getMaxDeltaX ()
	{
		return maxDeltaX;
	}

	public void setMaxDeltaX (double value)
	{
		this.maxDeltaX = value;
	}

	public double getMaxDeltaY ()
	{
		return maxDeltaY;
	}

	public void setMaxDeltaY (double value)
	{
		this.maxDeltaY = value;
	}

	public double getDeltaX ()
	{
		return deltaX;
	}

	public void setDeltaX (double value)
	{
		this.deltaX = clamp (value, minDeltaX, maxDeltaX);
	}
}
