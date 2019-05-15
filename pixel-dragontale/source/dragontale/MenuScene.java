package dragontale;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class MenuScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;

    public MenuScene (Assets a, int width, int height)
    {
        this.a = a;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update (SceneManager mgr, long elapsedMillis)
    {
    }

    @Override
    public void render (Graphics g)
    {
        a.getFont (0).draw (g, "Hello", 100,  80);
        a.getFont (1).draw (g, "Hello", 100, 160);
        a.getFont (2).draw (g, "Hello", 100, 240);
        a.getFont (3).draw (g, "Hello", 100, 320);
        a.getFont (4).draw (g, "Hello", 100, 400);
        a.getFont (5).draw (g, "Hello", 100, 480);
    }

    @Override
    public void activate ()
    {
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
    }
}
