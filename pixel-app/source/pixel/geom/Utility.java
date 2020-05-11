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
        return collision_point_point (a.getX (), a.getY (), b.getX (), b.getY ());
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

    public static boolean collision (Point p, Circle c)
    {
        double px = p.getX ();
        double py = p.getY ();
        double cx = c.getCenter ().getX ();
        double cy = c.getCenter ().getY ();
        double r = c.getRadius ();

        return collision_point_circle (px, py, cx, cy, r);
    }

    public static boolean collision_point_circle (double px, double py, double cx, double cy, double r)
    {
        double d = Point.distance (px, py, cx, cy);

        return d <= r;
    }

    public static boolean collision (Circle a, Circle b)
    {
        double ax = a.getCenter ().getX ();
        double ay = a.getCenter ().getY ();
        double ar = a.getRadius ();

        double bx = b.getCenter ().getX ();
        double by = b.getCenter ().getY ();
        double br = b.getRadius ();

        return collision_circle_circle (ax, ay, ar, bx, by, br);
    }

    public static boolean collision_circle_circle (double ax, double ay, double ar, double bx, double by, double br)
    {
        double d = Point.distance (ax, ay, bx, by);

        return d <= ar + br;
    }

    public static boolean collision (Line l, Circle c)
    {
        double x1 = l.getA ().getX ();
        double y1 = l.getA ().getY ();
        double x2 = l.getB ().getX ();
        double y2 = l.getB ().getY ();
        double cx = c.getCenter ().getX ();
        double cy = c.getCenter ().getY ();
        double r = c.getRadius ();

        return collision_line_circle (x1, y1, x2, y2, cx, cy, r);
    }

    public static boolean collision_line_circle (double x1, double y1, double x2, double y2, double cx, double cy, double r)
    {
          double len = Point.distance (x1, y1, x2, y2);
          double dot = (((cx - x1) * (x2 - x1)) + ((cy - y1) * (y2 - y1)) ) / (len * len);
          double closestX = x1 + (dot * (x2 - x1));
          double closestY = y1 + (dot * (y2 - y1));
          double distance = Point.distance (closestX, closestY, cx, cy);

          return distance <= r;
    }

    public static boolean collision (Point p, Rectangle r)
    {
        double px = p.getX ();
        double py = p.getY ();
        double rx = r.getTopLeft ().getX ();
        double ry = r.getTopLeft ().getY ();
        double rw = r.getWidth ();
        double rh = r.getHeight ();

        return collision_point_rectangle (px, py, rx, ry, rw, rh);
    }

    public static boolean collision_point_rectangle (double px, double py, double rx, double ry, double rw, double rh)
    {
        return px >= rx
            && px <= rx + rw
            && py >= ry
            && py <= ry + rh;
    }

    public static Optional<List<Point>> collision (Line l, Rectangle r)
    {
        double x1 = l.getA ().getX ();
        double y1 = l.getA ().getY ();
        double x2 = l.getB ().getX ();
        double y2 = l.getB ().getY ();
        double rx = r.getTopLeft ().getX ();
        double ry = r.getTopLeft ().getY ();
        double rw = r.getWidth ();
        double rh = r.getHeight ();

        return collision_line_rectangle (x1, y1, x2, y2, rx, ry, rw, rh);
    }

    public static Optional<List<Point>> collision_line_rectangle (double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh)
    {
        List<Point> list = new ArrayList<> ();
        Optional<Point> left = collision_line_line (x1, y1, x2, y2, rx, ry, rx, ry + rh);
        Optional<Point> right = collision_line_line (x1, y1, x2, y2, rx + rw, ry, rx + rw,ry + rh);
        Optional<Point> top = collision_line_line (x1, y1, x2, y2, rx, ry, rx + rw, ry);
        Optional<Point> bottom = collision_line_line (x1, y1, x2, y2, rx, ry + rh, rx + rw,ry + rh);

        if (left.isPresent ()) list.add (left.get ());
        if (right.isPresent ()) list.add (right.get ());
        if (top.isPresent ()) list.add (top.get ());
        if (bottom.isPresent ()) list.add (bottom.get ());

        if (list.isEmpty ())
        {
            if (collision_point_rectangle (x1, y1, rx, ry, rw, rh) &&
                collision_point_rectangle (x2, y2, rx, ry, rw, rh))
            {
                return Optional.of (list);
            }
            else
            {
                return Optional.empty ();
            }
        }

        return Optional.of (list);
    }

    public static boolean collision (Circle c, Rectangle r)
    {
        double cx = c.getCenter ().getX ();
        double cy = c.getCenter ().getY ();
        double radius = c.getRadius ();
        double rx = r.getTopLeft ().getX ();
        double ry = r.getTopLeft ().getY ();
        double rw = r.getWidth ();
        double rh = r.getHeight ();

        return collision_circle_rectangle (cx, cy, radius, rx, ry, rw, rh);
    }

    public static boolean collision_circle_rectangle (double cx, double cy, double radius, double rx, double ry, double rw, double rh)
    {
        double testX = cx;
        double testY = cy;

        if (cx < rx)
            testX = rx;
        else if (cx > rx + rw)
            testX = rx + rw;

        if (cy < ry)
            testY = ry;
        else if (cy > ry + rh)
            testY = ry + rh;

        double distance = Point.distance (cx, cy, testX, testY);

        return distance <= radius;
    }

    public static boolean collision (Rectangle r1, Rectangle r2)
    {
        double x1 = r1.getTopLeft ().getX ();
        double y1 = r1.getTopLeft ().getY ();
        double w1 = r1.getWidth ();
        double h1 = r1.getHeight ();
        double x2 = r2.getTopLeft ().getX ();
        double y2 = r2.getTopLeft ().getY ();
        double w2 = r2.getWidth ();
        double h2 = r2.getHeight ();

        return collision_rectangle_rectangle (x1, y1, w1, h1, x2, y2, w2, h2);
    }

    public static boolean collision_rectangle_rectangle (double x1, double y1, double w1, double h1, double x2, double y2, double w2, double h2)
    {
        return x1 + w1 >= x2
            && x1 <= x2 + w2
            && y1 + h1 >= y2
            && y1 <= y2 + h2;
    }
}
