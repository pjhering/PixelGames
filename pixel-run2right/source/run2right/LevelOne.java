package run2right;

import java.awt.Graphics;
import pixel.Assets;
import pixel.maps.LevelFile;
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
        a.getFont (0).draw (g, "LIVES:  0", 10, 20);
        a.getFont (0).draw (g, "POINTS: 0", 10, 40);
    }
}
