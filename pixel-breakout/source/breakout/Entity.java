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

    public Entity (double x1, double y1, double x2, double y2, Sprite sprite)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.sprite = sprite;
    }

    public void render (Graphics g)
    {
        sprite.draw (g,
            (int) Math.round (x1),
            (int) Math.round (y1),
            (int) Math.round (x2),
            (int) Math.round (y2));
    }

    public boolean hits (Entity that)
    {
        return this.x1 < that.x2
            && this.x2 > that.x1
            && this.y1 < that.y2
            && this.y2 > that.y1;
    }
}
