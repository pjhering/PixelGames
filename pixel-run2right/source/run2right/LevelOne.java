package run2right;

public class LevelOne
{
    private TileMap tileMap;
    private Player player;

    public LevelOne ()
    {
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
        tileMap.update (elapsedMillis);
        return true;
    }

    public void draw (Graphics g)
    {
        tileMap.draw (g);
        a.getFont (0).draw (g, "LIVES:  0", 10, 20);
        a.getFont (0).draw (g, "POINTS: 0", 10, 40);
    }
}
