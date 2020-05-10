package pixel.geom;

public class Utility
{
    private static final double BUFFER = 0.01;

    private Utility () {/*NOT USED*/}

    public static boolean collision (Point a, Point b)
    {
        return point_point_collision (a.getX (), a.getY (). b.getX (), b.getY ());
    }

    public static boolean point_point_collision (double x1, double y1, double x2, double y2)
    {
        return x1 > x2 - BUFFER
            && x1 < x2 + BUFFER
            && y1 > y2 - BUFFER
            && y1 < y2 + BUFFER;
    }
}
