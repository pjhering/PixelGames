package run2right;

import java.awt.Graphics;
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

    public LevelOne (Assets a)
    {
		this.a = a;

        try
        {
            this.tileMap = LevelFile.load ("/maps/level1.json").getTileMap ();
            List<MapObject> objectLayer = tileMap.getObjectLayer ();
            for (MapObject obj : objectLayer)
            {
                Map<String, Object> properties = obj.getProperties ();

                if (properties.containsKey ("name"))
                {
                    String name = (String) properties.get ("name");

                    switch (name)
                    {
                        case "player":
                            player = new Player (obj, a);
                            break;
                    }
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
        return true;
    }

    public void draw (Graphics g)
    {
        tileMap.draw (g);
        player.draw (g, 0, 0);
        a.getFont (0).draw (g, "LIVES:  0", 10, 20);
        a.getFont (0).draw (g, "POINTS: 0", 10, 40);
    }
}
