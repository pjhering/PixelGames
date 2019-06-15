package spacebattle;

import java.awt.Graphics;

public abstract class Entity
{
	public Entity (double x, double y, double radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public boolean hits (Entity that)
	{
		double xsq = Math.pow (this.x - that.x, 2);
		double ysq = Math.pow (this.y - that.y, 2);
		double dsq = xsq + ysq;
		double rsq = Math.pow (this.r + that.r, 2);

		return dsq < rsq;
	}

	public abstract void update (long elapsedMillis);

	public abstract void draw (Graphics g);
}