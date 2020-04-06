package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import static spaceinvaders.Main.GROUND_COLOR;

public class Ground extends Entity
{
    public static final double SPEED = 1.0;
    private boolean move;
    private boolean left;
    private int w;
    private int h;

    public Ground (double x1, double y1, double x2, double y2)
    {
        super (x1, y1, x2, y2);
        w = (int)Math.round (getWidth ());
        h = (int)Math.round (getHeight ());
    }

    public void update (long elapsed)
    {
        // NOT USED
    }

    public void draw (Graphics g)
    {
        int x = (int)Math.round (x1);
        int y = (int)Math.round (y1);

        g.setColor (GROUND_COLOR);
        g.fillRect (x, y, w, h);

    }
}
