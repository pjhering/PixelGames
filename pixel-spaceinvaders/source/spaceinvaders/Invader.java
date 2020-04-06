package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;

public class Invader extends Entity
{
    private Color[] colors;
    private int index;
    private long colorStartTime;
    private int w;
    private int h;
    private double speed;

    public Invader (double x1, double y1, double x2, double y2, Color[] colors)
    {
        super (x1, y1, x2, y2);
        this.colors = colors;
        this.index = 0;
        this.colorStartTime = System.currentTimeMillis ();
        w = (int)Math.round (getWidth ());
        h = (int)Math.round (getHeight ());
    }

    public void update (long elapsed)
    {
        long diff = System.currentTimeMillis () - colorStartTime;

        if (diff > 1000)
        {
            index = (index + 1) % colors.length;
            colorStartTime = System.currentTimeMillis ();
        }
    }

    public void draw (Graphics g)
    {
        int x = (int)Math.round (x1);
        int y = (int)Math.round (y1);

        g.setColor (colors[index]);
        g.fillRect (x, y, w, h);

        g.setColor (colors[index].brighter ());
        g.drawRect (x, y, w, h);
    }

    public void setSpeed (double value)
    {
        this.speed = value;
    }
}
