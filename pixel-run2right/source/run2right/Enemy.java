package run2right;

import java.awt.Color;
import java.awt.Graphics;
import pixel.Assets;
import pixel.Sprite;
import pixel.maps.MapObject;

public class Enemy extends Actor
{
    private Sprite sprite;

	public Enemy (MapObject obj, Assets a)
	{
		super (obj, a);
        this.sprite = a.getSprite (8).duplicate ();
	}

	public boolean update (long elapsed)
	{
		return true;
	}

	public void draw (Graphics g, int xOffset, int yOffset)
	{
        int x1 = (int) (object.getX () - xOffset);
        int y1 = (int) (object.getY () - yOffset);
        int x2 = x1 + (int) object.getWidth ();
        int y2 = y1 + (int) object.getHeight ();

        sprite.draw (g, x1, y1, x2, y2);
	}
}
