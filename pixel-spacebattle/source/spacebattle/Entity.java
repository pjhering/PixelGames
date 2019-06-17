package spacebattle;

import java.awt.Graphics;

public abstract class Entity
{
    protected double x;
    protected double y;
    protected double radius;
    protected double deltaX, minDeltaX, maxDeltaX;
    protected double deltaY, minDeltaY, maxDeltaY;

	public Entity (double x, double y, double radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
        this.deltaX = this.deltaY = 0.0;
        this.minDeltaX = this.minDeltaY = Double.MIN_VALUE;
        this.maxDeltaX = this.maxDeltaY = Double.MAX_VALUE;
	}

	public abstract void update (long elapsedMillis);

	public abstract void draw (Graphics g);

	public boolean hits (Entity that)
	{
		double xsq = Math.pow (this.x - that.x, 2);
		double ysq = Math.pow (this.y - that.y, 2);
		double dsq = xsq + ysq;
		double rsq = Math.pow (this.radius + that.radius, 2);

		return dsq < rsq;
	}

    public void accelerate (double xAmount, double yAmount)
    {
        deltaX = Math.min (maxDeltaX, Math.max (minDeltaX, deltaX + xAmount));
        deltaY = Math.min (maxDeltaY, Math.max (minDeltaY, deltaY + yAmount));
    }

    public void setDelta (double xAmount, double yAmount)
    {
        deltaX = Math.min (maxDeltaX, Math.max (minDeltaX, xAmount));
        deltaY = Math.min (maxDeltaY, Math.max (minDeltaY, yAmount));
    }

    public void setMinDelta (double xAmount, double yAmount)
    {
        minDeltaX = xAmount;
        minDeltaY = yAmount;
    }

    public void setMaxDelta (double xAmount, double yAmount)
    {
        maxDeltaX = xAmount;
        maxDeltaY = yAmount;
    }

    public double getX ()
    {
        return this.x;
    }

    public void setX (double value)
    {
        this.x = value;
    }

    public double getY ()
    {
        return this.y;
    }

    public void setY (double value)
    {
        this.y = value;
    }

    public double getRadius ()
    {
        return this.radius;
    }

    public void setRadius (double value)
    {
        this.radius = value;
    }
}
