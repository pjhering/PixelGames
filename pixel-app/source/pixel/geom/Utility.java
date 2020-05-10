package pixel.geom;

public class Utility
{
    private static final double BUFFER = 0.01;

    private Utility () {/*NOT USED*/}

    public static boolean collision (Point a, Point b)
    {
        return point_point_collision (a.getX (), a.getY (). b.getX (), b.getY ());
    }

    public static boolean collision_point_point (double x1, double y1, double x2, double y2)
    {
        return x1 > x2 - BUFFER
            && x1 < x2 + BUFFER
            && y1 > y2 - BUFFER
            && y1 < y2 + BUFFER;
    }

    public static boolean collision (Point p, Line l)
    {
        double px = p.getX ();
        double py = p.getY ();
        double x1 = l.getA ().getX ();
        double y1 = l.getA ().getY ();
        double x2 = l.getB ().getX ();
        double y2 = l.getB ().getY ();

        return point_line_collision (px, py, x1, y1, x2, y2);
    }

    public static boolean collision_point_line (double px, double py, double x1, double y1, double x2, double y2)
    {
        float d1 = Point.distance (px, py, x1, y1);
        float d2 = Point.distance (px, py, x2, y2);
        float lineLen = Point.distance (x1, y1, x2, y2);

        return d1 + d2 >= lineLen - BUFFER
            && d1 + d2 <= lineLen + BUFFER;
    }
}
