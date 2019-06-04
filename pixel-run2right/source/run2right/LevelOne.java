package run2right;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pixel.Assets;
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

//ERASE THIS STUFF
	private boolean left, up;
	private double playerMinX, playerMinY;
	private double playerMaxX, playerMaxY;
//ERASE THIS STUFF

    public LevelOne (Assets a, int width, int height)
    {
		this.a = a;
        this.enemies = new ArrayList<> ();
		this.camera = new Camera (width, height);

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
//ERASE THIS STUFF
						left = false;
						up = false;
						playerMinX = 0.0;
						playerMinY = 0.0;
						playerMaxX = tileMap.getMapWidth () - obj.getWidth ();
						playerMaxY = tileMap.getMapHeight () - obj.getHeight ();
//ERASE THIS STUFF
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
		movePlayer ();
		camera.update (tileMap, player);
        return true;
    }

	private void movePlayer ()
	{
		double x = player.getMapObject ().getX ();
		double y = player.getMapObject ().getY ();

// ERASE THIS STUFF
		if (left)
		{
			x -= 1.0;

			if (x <= playerMinX)
			{
				left = false;
			}
		}
		else
		{
			x += 1.0;

			if (x >= playerMaxX)
			{
				left = true;
			}
		}
// ERASE THIS STUFF
		if (up)
		{
			y -= 1.0;

			if (y <= playerMinY)
			{
				up = false;
			}
		}
		else
		{
			y += 1.0;

			if (y >= playerMaxY)
			{
				up = true;
			}
		}

		player.getMapObject ().setX (x);
		player.getMapObject ().setY (y);
	}

    public void draw (Graphics g)
    {
		camera.render (g, tileMap);
		camera.render (g, player);
		camera.render (g, enemies);

        a.getFont (0).draw (g, "LIVES:  0", 10, 20);
        a.getFont (0).draw (g, "POINTS: 0", 10, 40);
    }
}
