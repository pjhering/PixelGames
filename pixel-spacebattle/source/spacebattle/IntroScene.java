package spacebattle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Objects;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class IntroScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private boolean exit;
    private final String TEXT;
    private final int x;
	private StarField bg;

    public IntroScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);
        this.TEXT = a.getString (0);
        this.x = (width - a.getFont (5).getWidth (TEXT)) / 2;
		this.bg = new StarField (width, height, 0.05, 300);
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (exit)
        {
            mgr.replace (new GameScene (a, width, height));
        }
		else
		{
			bg.update (elapsedMillis);
		}
    }

    public void render (Graphics g)
    {
		bg.draw (g);
        a.getFont (5).draw (g, TEXT, x, 200);
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
        if (e.getKeyCode () == KeyEvent.VK_ENTER)
        {
            exit = true;
        }
    }
}
