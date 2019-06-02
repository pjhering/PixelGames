package pixel;

import java.awt.Graphics;

public class NullSprite implements Sprite
{
	private final int width;
	private final int height;

	public NullSprite (int width, int height)
	{
		this.width = width;
		this.height = height;
	}

    public int getWidth ()
	{
		return width;
	}

    public int getHeight ()
	{
		return height;
	}

    public void draw (Graphics g, int dx1, int dy1, int dx2, int dy2)
	{
		// not implemented
	}

    public void reset ()
	{
		// not implemented
	}

    public Sprite duplicate ()
    {
        return new NullSprite (width, height);
    }
}
