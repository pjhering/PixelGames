package run2right;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
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
	private LevelOne level1;

    public GameScene (Assets a, int width, int height)
    {
        this.a = a;
        this.width = Math.abs (width);
        this.height = Math.abs (height);
		this.level1 = new LevelOne (a);
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
    }

    public void render (Graphics g)
    {
		level1.draw (g);
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
