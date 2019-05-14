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
