package dragontale;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class CreditsScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private final String[] credits = {
        "This whole thing was made",
        "by Pete Hering using his",
        "excellent PixelApp game",
        "framework, but...",
        "He used ForeignGuyMike's videos",
        "and assets for much of it."
    };
    private boolean enter;

    public CreditsScene (Assets a, int width, int height)
    {
        this.a = a;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (enter)
        {
            mgr.replace (new MenuScene (a, width, height));
        }
    }

    @Override
    public void render (Graphics g)
    {
        g.setColor (Color.BLACK);
        g.fillRect (0, 0, width, height);

        for (int i = 0; i < credits.length; i++)
        {
            a.getFont (1).draw (g, credits[i], 20, 50 + (i * 32));
        }
    }

    @Override
    public void activate ()
    {
        enter = false;
    }

    @Override
    public void deactivate ()
    {
    }

    @Override
    public void dispose ()
    {
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        if (e.getKeyCode () == KeyEvent.VK_ESCAPE)
        {
            enter = true;
        }
    }
}
