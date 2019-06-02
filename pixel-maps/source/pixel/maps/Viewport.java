package pixel.maps;

public class Viewport
{
    public final int width;
    public final int height;
    private int x1, y1, x2, y2;

    public Viewport (int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void update (TileMap map, MapObject obj)
    {
        int ox1 = (int) obj.getLeft ();
        int ox2 = (int) obj.getRight ();
        int oy1 = (int) obj.getTop ();
        int oy2 = (int) obj.getBottom ();

        x1 = Math.min (Math.max (0, ox1 - width / 2), map.getMapWidth () - width);
        x2 = x1 + width;
        y1 = Math.min (Math.max (0, oy1 - height / 2), map.getMapHeight () - height);
        y2 = y1 + height;
        //
        // System.out.printf ("%d, %d - %d, %d\n", x1, y1, x2, y2);
    }

    public int getX1 ()
    {
        return x1;
    }

    public int getX2 ()
    {
        return x2;
    }

    public int getY1 ()
    {
        return y1;
    }

    public int getY2 ()
    {
        return y2;
    }
}
