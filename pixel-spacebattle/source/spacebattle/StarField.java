package spacebattle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;

public class StarField
{
	private final int width;
	private final int height;
	private final Random r;
	private double deltaY;
	private Point2D[] stars;

	public StarField (int width, int height, double deltaY, int count)
	{
		this.width = width;
		this.height = height;
		this.r = new Random ();

		this.deltaY = deltaY;
		this.stars = new Point2D[count];

		for (int i = 0; i < count; i++)
		{
			double x = (int)(r.nextDouble() * width);
			double y = (int)(r.nextDouble() * height);

			this.stars[i] = new Point2D.Double (x, y);
		}
	}

	public void update (long elapsedMillis)
	{
		double amount = deltaY * elapsedMillis;

		for (int i = 0; i < stars.length; i++)
		{
			double x = stars[i].getX ();
			double y = stars[i].getY () + amount;

			if (y < 0)
			{
				y = height;
				x = (int)(r.nextDouble () * width);
			}
			else if (y > height)
			{
				y = 0;
				x = (int)(r.nextDouble () * width);
			}

			stars[i].setLocation (x, y);
		}
	}

	public void draw (Graphics g)
	{
		g.setColor (Color.BLACK);
		g.fillRect (0, 0, width, height);

		g.setColor (Color.WHITE);
		for (int i = 0; i < stars.length; i++)
		{
			int x = (int) stars[i].getX ();
			int y = (int) stars[i].getY ();
			g.fillRect (x, y, 2, 2);
		}
	}
}
