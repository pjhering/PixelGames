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
    private Background bg;

    public Level1Scene (Assets a, int width, int height)
    {
        this.a = a;
        this.width = width;
        this.height = height;

        this.tileMap = new TileMap (60);
        this.tileMap.loadTiles (a);
        this.tileMap.loadMap (a);
        this.tileMap.setPosition (0, 0);

        this.bg = new Background (a.getImage (0), 0.1);
        this.bg.setVector (0.15, 0);
    }

    @Override
    public void update (SceneManager mgr, long elapsedMillis)
    {
        bg.update (elapsedMillis);
    }

    @Override
    public void render (Graphics g)
    {
        bg.draw (g);
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
