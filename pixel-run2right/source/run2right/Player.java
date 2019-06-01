package run2right;

import java.awt.Color;
import java.awt.Graphics;
import pixel.Assets;
import pixel.Sprite;
import pixel.maps.MapObject;

public class Player extends Actor
{
    private static final int LEFT  = 0;
    private static final int RIGHT = 1;
    private static final int UP    = 2;
    private static final int DOWN  = 3;
    private static final int STAND = 4;
    private static final int DEAD  = 5;

    private Sprite[] sprites;
    private int index;

	public Player (MapObject obj, Assets a)
	{
		super (obj, a);
        sprites = new Sprite[]
        {
            a.getSprite(0),
            a.getSprite(1),
            a.getSprite(2),
            a.getSprite(3),
            a.getSprite(4),
            a.getSprite(5)
        };
        index = STAND;
	}

    public void changeSprite (int i)
    {
        if (index != i)
        {
            index = i;
            sprites[index].reset ();
        }
    }

	public boolean update (long elapsed)
	{
		return true;
	}

	public void draw (Graphics g, int xOffset, int yOffset)
	{
		int x1 = xOffset + (int) object.getX ();
		int y1 = yOffset + (int) object.getY ();
		int x2 = x1 + (int) object.getWidth ();
		int y2 = y1 + (int) object.getHeight ();

		a.getSprite (4).draw (g, x1, y1, x2, y2);
	}
}
