package spaceinvaders;

import java.awt.Graphics;
import static pixel.Utility.debug;
import static spaceinvaders.Main.BULLET_COLOR1;
import static spaceinvaders.Main.BULLET_DOWN_SPEED;
import static spaceinvaders.Main.BULLET_HEIGHT;
import static spaceinvaders.Main.BULLET_UP_SPEED;
import static spaceinvaders.Main.BULLET_WIDTH;

public class Bullet extends Entity
{
    private boolean up;
    private int w;
    private int h;

    public Bullet (double x1, double y1, double x2, double y2, boolean up)
    {
        super (x1, y1, x2, y2);
        this.up = up;
        w = (int)Math.round (getWidth ());
        h = (int)Math.round (getHeight ());
    }

    public static Bullet createFor (Player p)
    {
        double x1 = p.getX1 () + ((p.getWidth () - BULLET_WIDTH) / 2.0);
        double x2 = x1 + BULLET_WIDTH;
        double y1 = p.getY1 () + ((p.getHeight () - BULLET_HEIGHT) / 2.0);
        double y2 = y1 + BULLET_HEIGHT;

        return new Bullet (x1, y1, x2, y2, true);
    }

    public static Bullet createFor (Invader i)
    {
        double x1 = i.getX1 () + ((i.getWidth () - BULLET_WIDTH) / 2.0);
        double x2 = x1 + BULLET_WIDTH;
        double y1 = i.getY1 () + ((i.getHeight () - BULLET_HEIGHT) / 2.0);
        double y2 = y1 + BULLET_HEIGHT;

        return new Bullet (x1, y1, x2, y2, false);
    }

    public void update (long elapsed)
    {
        if (up)
        {
            double dy = elapsed * BULLET_UP_SPEED;
            y1 += dy;
            y2 += dy;
        }
        else
        {
            double dy = elapsed * BULLET_DOWN_SPEED;
            y1 += dy;
            y2 += dy;
        }
    }

    public void draw (Graphics g)
    {
        int x = (int)Math.round (x1);
        int y = (int)Math.round (y1);

        g.setColor (BULLET_COLOR1);
        g.fillRect (x, y, w, h);
    }
}
