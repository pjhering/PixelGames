package pixel.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Utility
{
    private static final double BUFFER = 0.01;

    private Utility () {/*NOT USED*/}

    public static boolean collision (Point a, Point b)
    {
        return collision_point_point (a.getX (), a.getY (). b.getX (), b.getY ());
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

        return collision_point_line (px, py, x1, y1, x2, y2);
    }

    public static boolean collision_point_line (double px, double py, double x1, double y1, double x2, double y2)
    {
        double d1 = Point.distance (px, py, x1, y1);
        double d2 = Point.distance (px, py, x2, y2);
        double lineLen = Point.distance (x1, y1, x2, y2);

        return d1 + d2 >= lineLen - BUFFER
            && d1 + d2 <= lineLen + BUFFER;
    }

    public static Optional<Point> collision (Line a, Line b)
    {
        double x1 = a.getA ().getX ();
        double y1 = a.getA ().getY ();
        double x2 = a.getB ().getX ();
        double y2 = a.getB ().getY ();
        double x3 = b.getA ().getX ();
        double y3 = b.getA ().getY ();
        double x4 = b.getB ().getX ();
        double y4 = b.getB ().getY ();

        return collision_line_line (x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public static Optional<Point> collision_line_line (double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
    {
        double uA = ((x4 - x3) * (y1 - y3)
                   - (y4 - y3) * (x1 - x3))
                  / ((y4 - y3) * (x2 - x1)
                   - (x4 - x3) * (y2 - y1));

        double uB = ((x2 - x1) * (y1 - y3)
                   - (y2 - y1) * (x1 - x3))
                  / ((y4 - y3) * (x2 - x1)
                   - (x4 - x3) * (y2 - y1));

        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1)
        {
            double ix = x1 + (uA * (x2 - x1));
            double iy = y1 + (uA * (y2 - y1));

            return Optional.of (new Point (ix, iy));
        }

        return Optional.empty ();
    }
}
