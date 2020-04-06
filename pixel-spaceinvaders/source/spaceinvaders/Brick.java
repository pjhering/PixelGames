package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;

public class Brick extends Entity
{
    private int w;
    private int h;

    public Brick (double x1, double y1, double x2, double y2)
    {
        super (x1, y1, x2, y2);
        w = (int)Math.round (getWidth ());
        h = (int)Math.round (getHeight ());
    }

    public void update (long elapsed)
    {
    }

    public void draw (Graphics g)
    {
        int x = (int)x1;
        int y = (int)y1;

        g.setColor (Main.BRICK_COLOR);
        g.fillRect (x, y, w, h);
        g.setColor (Main.MORTAR_COLOR);
        g.drawRect (x, y, w, h);
    }
}
