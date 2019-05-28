package run2right;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Objects;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;
import pixel.maps.LevelFile;
import pixel.maps.TileMap;

public class GameScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
	private boolean exit;

    public GameScene (Assets a, int width, int height)
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
        if (e.getKeyCode () == KeyEvent.VK_ESCAPE)
        {
            exit = true;
        }
    }
}
