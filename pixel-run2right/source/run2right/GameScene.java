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
	private TileMap tileMap;

    public GameScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);
		try
		{
			this.tileMap = LevelFile.load ("/maps/level1.json").getTileMap ();
		}
		catch (Exception ex)
		{
			throw new RuntimeException (ex);
		}
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
    }

    public void render (Graphics g)
    {
		// g.setColor (Color.WHITE);
		// g.fillRect (0, 0, width, height);
		// a.getFont (0).draw (g, getClass ().getSimpleName (), 10, 30);
		tileMap.draw (g);
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
