package dragontale;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background
{
	private BufferedImage image;
    private int width;
    private int height;
	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveScale;

	public Background(BufferedImage image, double ms)
	{
        this.image = image;
        this.width = image.getWidth ();
        this.height = image.getHeight ();
        this.moveScale = ms;
	}

	public void setPosition(double x, double y)
	{
		this.x = (x * moveScale) % width;
		this.y = (y * moveScale) % height;
	}

	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

	public void update(long elapsedMillis)
	{
		x += dx;
        if (x > width) x = 0;
        if (x < -width) x = width;
        
		y += dy;
	}

	public void draw(Graphics g)
	{
		g.drawImage(image, (int)x, (int)y, null);
		if (x < 0)
		{
			g.drawImage(image, (int)x + width, (int)y, null);
		}
		if (x > 0)
		{
			g.drawImage(image, (int)x - width, (int)y, null);
		}
	}
}
