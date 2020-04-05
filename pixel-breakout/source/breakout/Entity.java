package breakout;

import java.awt.Graphics;
import pixel.Sprite;

public class Entity
{
    protected double x1;
    protected double y1;
    protected double x2;
    protected double y2;
    protected Sprite sprite;
    protected boolean visible;

    public Entity (double x1, double y1, double x2, double y2, Sprite sprite)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.sprite = sprite;
        this.visible = true;
    }

    public void render (Graphics g)
    {
        if (visible)
        {
            sprite.draw (g,
                (int) Math.round (x1),
                (int) Math.round (y1),
                (int) Math.round (x2),
                (int) Math.round (y2));
        }
    }

    public boolean hits (Entity that)
    {
        return this.x1 < that.x2
            && this.x2 > that.x1
            && this.y1 < that.y2
            && this.y2 > that.y1;
    }

    public double getX1 () { return x1; }
    public double getY1 () { return y1; }
    public double getX2 () { return x2; }
    public double getY2 () { return y2; }
    public double getWidth () { return x2 - x1; }
    public double getHeight () { return y2 - y1; }
    public boolean isVisible () { return visible; }
    public void setVisible (boolean value) { visible = value; }
}
