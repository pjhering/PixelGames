package dragontale;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class Level1Scene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private TileMap tileMap;

    public Level1Scene (Assets a, int width, int height)
    {
        this.a = a;
        this.width = width;
        this.height = height;
        this.tileMap = new TileMap (60);
        this.tileMap.loadTiles (a);
        this.tileMap.loadMap (a);
        this.tileMap.setPosition (0, 0);
    }

    @Override
    public void update (SceneManager mgr, long elapsedMillis)
    {
    }

    @Override
    public void render (Graphics g)
    {
        g.setColor (Color.WHITE);
        g.fillRect (0, 0, width, height);

        tileMap.draw (g);
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
