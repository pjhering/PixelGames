package pixel.geom;

public class Rectangle
{
    private Point topLeft;
    private double width;
    private double height;

    public Rectangle (Point topLeft, double width, double height)
    {
        this.topLeft = topLeft;
        this.width = Math.abs (width);
        this.height = Math.abs (height);
    }

    public static double area (Rectangle r)
    {
        return r.width * r.height;
    }

    public double area ()
    {
        return Rectangle.area (this);
    }

    public Point getTopLeft () { return topLeft; }

    public double getWidth () { return width; }
    public void setWidth (double value) { width = Math.abs (value); }

    public double getHeight () { return height; }
    public void setHeight (double value) { height = Math.abs (value); }
}
