package run2right;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pixel.Assets;
import pixel.maps.Camera;
import pixel.maps.LevelFile;
import pixel.maps.MapObject;
import pixel.maps.TileMap;

public class LevelOne
{
	private Assets a;
    private TileMap tileMap;
    private Player player;
    private List<Enemy> enemies;
    private Camera camera;

    //ERASE THESE
    private double minPlayerX, maxPlayerX;
    private double minPlayerY, maxPlayerY;
    private boolean left, up;

    public LevelOne (Assets a, int width, int height)
    {
		this.a = a;
        this.camera = new Camera (width, height);
        this.enemies = new ArrayList<> ();

        try
        {
            this.tileMap = LevelFile.load ("/maps/level1.json").getTileMap ();
			System.out.printf ("rows: %d, columns: %d\n", tileMap.getRowCount (), tileMap.getColumnCount ());
            List<MapObject> objectLayer = tileMap.getObjectLayer ();
            for (MapObject obj : objectLayer)
            {
                switch (obj.getName ())
                {
                    case "player":
                        player = new Player (obj, a);
                        minPlayerX = 0.0;
                        minPlayerY = 0.0;
                        maxPlayerX = width - obj.getWidth ();
                        maxPlayerY = height - obj.getHeight ();
                        left = false;
                        up = false;
                        break;
                    case "enemy":
                        enemies.add (new Enemy (obj, a));
                        break;
                }
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException (ex);
        }
    }

    public boolean update (long elapsedMillis)
    {
        double x = player.getMapObject ().getX ();
        double y = player.getMapObject ().getY ();

        if (left)
        {
            x -= 1.0;

            if (x <= minPlayerX)
            {
                left = false;
            }
        }
        else
        {
            x += 1.0;

            if (x >= maxPlayerX)
            {
                left = true;
            }
        }

        if (up)
        {
            y -= 1.0;

            if (y <= minPlayerY)
            {
                up = false;
            }
        }
        else
        {
            y += 1.0;

            if (y >= maxPlayerY)
            {
                up = true;
            }
        }

        player.getMapObject ().setX (x);
        player.getMapObject ().setY (y);

        camera.getViewport ().update (tileMap, player.getMapObject ());
        return true;
    }

    public void draw (Graphics g)
    {
        // tileMap.draw (g);
        camera.render (g, tileMap);
        player.draw (g, 0, 0);
        // enemies.forEach (e -> e.draw (g, 0, 0));
        // a.getFont (0).draw (g, "LIVES:  0", 10, 20);
        // a.getFont (0).draw (g, "POINTS: 0", 10, 40);
    }
}
