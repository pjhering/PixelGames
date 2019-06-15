package spacebattle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Objects;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class PausedScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private boolean exit;
    private final String TEXT;
    private final int x;

    public PausedScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);
        this.TEXT = a.getString (1);
        this.x = (width - a.getFont (7).getWidth (TEXT)) / 2;
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (exit)
        {
            mgr.pop ();
        }
    }

    public void render (Graphics g)
    {
        g.setColor (a.getColor (0));
        g.fillRect (0, 0, width, height);
        a.getFont (7).draw (g, TEXT, x, 200);
    }

    public void activate ()
    {
        exit = false;
    }

    public void deactivate ()
    {
    }

    public void dispose ()
    {
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        if (e.getKeyCode () == KeyEvent.VK_R)
        {
            exit = true;
        }
    }
}
