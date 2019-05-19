package pixel.maps;

public abstract class MapObject
{
	protected double x;
	protected double y;
	protected double minX;
	protected double minY;
	protected double maxX;
	protected double maxY;
	protected double width;
	protected double height;
	protected double deltaX;
	protected double deltaY;
	protected double minDeltaX;
	protected double minDeltaY;
	protected double maxDeltaX;
	protected double maxDeltaY;

	public MapObject (double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.minX = this.minY = Double.MIN_VALUE;
		this.maxX = this.minY = Double.MAX_VALUE;
		this.width = width;
		this.height = height;
		this.deltaX = 0.0;
		this.deltaY = 0.0;
		this.minDeltaX = this.minDeltaY = Double.MIN_VALUE;
		this.maxDeltaX = this.maxDeltaY = Double.MAX_VALUE;
	}

	public boolean intersects (MapObject that)
	{
		return this.x < that.x + that.width
			&& this.y < that.y + that.height
			&& that.x < this.x + this.width
			&& that.y < this.y + this.height;
	}

	public void update (long elapsedMillis)
	{
		x = Math.min (maxX, Math.max (minX, x + deltaX * elapsedMillis));
		y = Math.min (maxY, Math.max (minY, y + deltaY * elapsedMillis));
	}

	public double getX ()
	{
		return x;
	}

	public void setX (double value)
	{
		this.x = value;
	}

	public double getY ()
	{
		return y;
	}

	public void setY (double value)
	{
		this.y = value;
	}

	public double getMinX ()
	{
		return minX;
	}

	public void setMinX (double value)
	{
		this.minX = value;
	}

	public double getMinY ()
	{
		return minY;
	}

	public void setMinY (double value)
	{
		this.minY = value;
	}

	public double getMaxX ()
	{
		return maxX;
	}

	public void setMaxX (double value)
	{
		this.maxX = value;
	}

	public double getMaxY ()
	{
		return maxY;
	}

	public void setMaxY (double value)
	{
		this.maxY = value;
	}

	public double getWidth ()
	{
		return width;
	}

	public void setWidth (double value)
	{
		this.width = value;
	}

	public double getHeight ()
	{
		return height;
	}

	public void setHeight (double value)
	{
		this.height = value;
	}

	public double getDeltaX ()
	{
		return deltaX;
	}

	public void setDeltaX (double value)
	{
		this.deltaX = value;
	}

	public double getDeltaY ()
	{
		return deltaY;
	}

	public void setDeltaY (double value)
	{
		this.deltaY = value;
	}

	public double getMinDeltaX ()
	{
		return minDeltaX;
	}

	public void setMinDeltaX (double value)
	{
		this.minDeltaX = value;
	}

	public double getMaxDeltaX ()
	{
		return maxDeltaX;
	}

	public void setMaxDeltaX (double value)
	{
		this.maxDeltaX = value;
	}

	public double getMinDeltaY ()
	{
		return minDeltaY;
	}

	public void setMinDeltaY (double value)
	{
		this.minDeltaY = value;
	}

	public double getMaxDeltaY ()
	{
		return maxDeltaY;
	}

	public void setMaxDeltaY (double value)
	{
		this.maxDeltaY = value;
	}
}
