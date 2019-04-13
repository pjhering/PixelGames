package breakout;

import java.awt.Graphics;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class CountDownScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private long elapsed;
    private boolean exit;
    private String text = "3";
    private int x;

    public CountDownScene (Assets a, int width, int height)
    {
        this.a = a;
        this.width = width;
        this.height = height;
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
        elapsed += elapsedMillis;

        if (elapsed > 3000)
        {
            mgr.pop ();
        }
        else
        {
            text = Long.toString (3 - (elapsed / 1000));
            x = (width - a.getFont (7).getWidth (text)) / 2;
        }
    }

    public void render (Graphics g)
    {
        g.setColor (a.getColor (0));
        g.fillRect (0, 0, width, height);
        a.getFont (7).draw (g, text, x, 200);
    }

    public void activate ()
    {
        elapsed = 0L;
        exit = false;
    }

    public void deactivate ()
    {
    }

    public void dispose ()
    {
    }
}
