package spacebattle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class GameScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private boolean paused;
    private boolean gameOver;
    private final PausedScene pausedScene;
    private final HUD hud;

    public GameScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);

        pausedScene = new PausedScene (a, width, height);

        hud = new HUD (a, 50, 3);
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (paused)
        {
            mgr.push (pausedScene);
        }
        else if (gameOver)
        {
            mgr.replace (new GameOverScene (a, width, height));
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
        hud.render (g);
    }

    public void activate ()
    {
        paused = false;
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
            case KeyEvent.VK_P:
                paused = true;
                break;
        }
    }
}
