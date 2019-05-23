package pixel.maps;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class TileMap
{
    private Background background;
    private TileLayer tileLayer;
    private final List<ObjectLayer> objectLayers;
    private final Camera camera;

    public TileMap ()
    {
        this.objectLayers = new ArrayList<> ();
        this.camera = new Camera ();
    }

    public TileMap (Background bg, TileLayer tl)
    {
        this ();
        this.background = bg;
        this.tileLayer = tl;
    }

    public void update (long elapsedMillis)
    {
    }

    public void draw (Graphics g)
    {
    }

    public Background getBackground ()
    {
        return background;
    }

    public void setBackground (Background value)
    {
        this.background = value;
    }

    public TileLayer getTileLayer ()
    {
        return tileLayer;
    }

    public void setTileLayer (TileLayer value)
    {
        this.tileLayer = value;
    }

    public List<ObjectLayer> getObjectLayers ()
    {
        return objectLayers;
    }

    public Camera getCamera ()
    {
        return camera;
    }
}
