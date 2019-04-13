package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class PlayScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private boolean gameOver;

    public PlayScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (gameOver)
        {
            mgr.replace (new AfterScene (a, width, height));
        }
        else
        {
            updateGame (elapsedMillis);
        }
    }

    private void updateGame (long elapsedMillis)
    {
    }

    public void render (Graphics g)
    {
        g.setColor (a.getColor (0));
        g.fillRect (0, 0, width, height);
        a.getSprite (0).draw (g, 0,  0, 32, 32);
        a.getSprite (1).draw (g, 0, 40, 32, 72);
        a.getSprite (2).draw (g, 0, 80, 32,112);
    }

    public void activate ()
    {
        gameOver = false;
    }

    public void deactivate ()
    {
    }

    public void dispose ()
    {
    }

    @Override
    public void mouseMoved (MouseEvent e)
    {
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        switch (e.getKeyCode ())
        {
            case KeyEvent.VK_Q:
                gameOver = true;
                break;
        }
    }
}
