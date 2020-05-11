package pixel.geom;

public class Circle
{
    private Point center;
    private double radius;

    public Circle (double x, double y, double radius)
    {
        this (new Point (x, y), radius);
    }

    public Circle (Point center, double radius)
    {
        this.center = center;
        this.radius = Math.abs (radius);
    }

    public static double area (Circle c)
    {
        return Math.PI * (c.radius * c.radius);
    }

    public double area ()
    {
        return Circle.area (this);
    }

    public Point getCenter () { return center; }

    public double getRadius () { return radius; }
    public void setRadius (double value) { radius = Math.abs (value); }
}
