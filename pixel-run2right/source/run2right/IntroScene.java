package run2right;

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

    public IntroScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
    }

    public void render (Graphics g)
    {
		g.setColor (Color.BLACK);
		g.fillRect (0, 0, width, height);
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
