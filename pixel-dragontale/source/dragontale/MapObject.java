package dragontale;

import java.awt.Color;
import java.awt.Graphics;
import pixel.Sprite;

public class MapObject
{
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Sprite sprite;
	protected double deltaX;
	protected double deltaY;

	public MapObject (double x, double y, double width, double height, Sprite sprite)
	{
		setX (x);
		setY (y);
		setWidth (width);
		setHeight (height);
		setSprite (sprite);
	}

	public void update (long elapsedMillis)
	{
		this.x += (elapsedMillis * deltaX);
		this.y += (elapsedMillis * deltaY);
	}

	public void draw (Graphics g, int xOffset, int yOffset, boolean debug)
	{
		int x1 = (int) (x + xOffset);
		int y1 = (int) (y + yOffset);
		int w = (int) width;
		int h = (int) height;

		if (this.sprite != null)
		{
			int x2 = x1 + w;
			int y2 = y1 + h;
			this.sprite.draw (g, x1, y1, x2, y2);
		}

		if (debug)
		{
			g.setColor (Color.CYAN);
			g.drawRect (x1, y1, w, h);
		}
	}

	public boolean hits (MapObject that)
	{
		return this.x < that.x + that.width
			&& this.y < that.y + that.height
			&& that.x < this.x + this.width
			&& that.y < this.y + this.height;
	}

    public void setX (double value)
	{
		this.x = value;
	}

    public double getX ()
	{
		return this.x;
	}

    public void setY (double value)
	{
		this.y = value;
	}

    public double getY ()
	{
		return this.y;
	}

    public void setWidth (double value)
	{
		this.width = value;
	}

    public double getWidth ()
	{
		return this.width;
	}

    public void setHeight (double value)
	{
		this.height = value;
	}

    public double getHeight ()
	{
		return this.height;
	}

    public void setSprite (Sprite value)
	{
		this.sprite = value;
	}

    public Sprite getSprite ()
	{
		return this.sprite;
 	}

    public void setDeltaX (double value)
	{
		this.deltaX = value;
	}

    public double getDeltaX ()
	{
		return this.deltaX;
	}

	public void changeDeltaX (double amount, double min, double max)
	{
		Math.min (max, Math.max (min, this.deltaX + amount));
	}

	public boolean isMovingLeft ()
	{
		return this.deltaX < 0.0;
	}

	public boolean isMovingRight ()
	{
		return this.deltaX > 0.0;
	}

    public void setDeltaY (double value)
	{
		this.deltaY = value;
	}

    public double getDeltaY ()
	{
		return this.deltaY;
	}

	public void changeDeltaY (double amount, double min, double max)
	{
		Math.min (max, Math.max (min, this.deltaY + amount));
	}

	public boolean isMovingUp ()
	{
		return this.deltaY < 0.0;
	}

	public boolean isMovingDown ()
	{
		return this.deltaY > 0.0;
	}
}
