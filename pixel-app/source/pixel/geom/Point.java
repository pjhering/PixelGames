package pixel.geom;

public class Point
{
    private double x;
    private double y;

    public Point (double x, double y)
    {
        this.x = x;
        thix.y = y;
    }

    public static distance (Point p1, Point p2)
    {
        return distance (p1.x, p1.y, p2.x, p2.y);
    }

    public static distance (double x1, double y1, double x2, double y2)
    {
        double dx = x1 - x2;
        double dy = y1 - y2;
        double sqx = dx * dx;
        double sqy = dy * dy;

        return Math.sqrt (sqx + sqy);
    }

    public double distance (Point that)
    {
        return Point.distance (this, that);
    }

    public double getX () { return x; }
    public void setX (double value) { x = value; }

    public double getY () { return y; }
    public void setY (double value) { y = value; }
}
