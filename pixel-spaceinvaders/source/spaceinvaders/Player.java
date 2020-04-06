package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import static pixel.Utility.debug;
import static spaceinvaders.Main.PLAYER_DRAW;
import static spaceinvaders.Main.PLAYER_FILL;
import static spaceinvaders.Main.PLAYER_FIRE_DELAY;
import static spaceinvaders.Main.PLAYER_SPEED;
import static spaceinvaders.Main.WIDTH;

public class Player extends Entity
{
    private boolean move;
    private boolean left;
    private int w;
    private int h;
    private long fireStartTime;
    private boolean firing;

    public Player (double x1, double y1, double x2, double y2)
    {
        super (x1, y1, x2, y2, true, true);
        w = (int)Math.round (getWidth ());
        h = (int)Math.round (getHeight ());
        firing = false;
    }

    public boolean fire ()
    {
        if (!firing)
        {
            firing = true;
            fireStartTime = System.currentTimeMillis ();
            return true;
        }

        return false;
    }

    public void update (long elapsed)
    {
        if (firing)
        {
            long fireElapsed = System.currentTimeMillis () - fireStartTime;

            if (fireElapsed > PLAYER_FIRE_DELAY)
            {
                firing = false;
            }
        }

        if (move)
        {
            double dx = elapsed * PLAYER_SPEED;

            if (left)
            {
                x1 -= dx;
                x2 -= dx;
            }
            else
            {
                x1 += dx;
                x2 += dx;
            }

            if (x1 < 0)
            {
                double d = x1 * -1;
                x1 += d;
                x2 += d;
            }
            else if (x2 > WIDTH)
            {
                double d = x2 - WIDTH;
                x1 -= d;
                x2 -= d;
            }
        }
    }

    public void goLeft ()
    {
        this.move = true;
        this.left = true;
    }

    public void goRight ()
    {
        this.move = true;
        this.left = false;
    }

    public void stop ()
    {
        this.move = false;
    }

    public void draw (Graphics g)
    {
        int x = (int)Math.round (x1);
        int y = (int)Math.round (y1);

        g.setColor (PLAYER_FILL);
        g.fillRect (x, y, w, h);

        g.setColor (PLAYER_DRAW);
        g.drawRect (x, y, w, h);
    }
}
