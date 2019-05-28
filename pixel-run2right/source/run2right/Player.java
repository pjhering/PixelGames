package run2right;

import java.awt.Graphics;
import pixel.maps.MapObject;

public class Player extends Actor
{
	public Player (MapObject obj)
	{
		super (obj);
	}

	public boolean update (long elapsed)
	{
		return true;
	}

	public void draw (Graphics g, int xOffset, int yOffset)
	{
	}
}
