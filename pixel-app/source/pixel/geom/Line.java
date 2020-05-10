package pixel.geom;

public class Line
{
    private Point a;
    private Point b;

    public Line (Point a, Point b)
    {
        this.a = a;
        this.b = b;
    }

    public static double length (Line l)
    {
        return Point.distance (l.a, l.b);
    }

    public double length ()
    {
        return Line.length (this);
    }

    public Point getA () { return a; }

    public Point getB () { return b; }
}
