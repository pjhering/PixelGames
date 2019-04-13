package breakout;

import java.awt.Graphics;
import pixel.Sprite;
import static pixel.Utility.clamp;
import static pixel.Utility.random;

public class Ball
{
    private static final double MIN_DELTA_X = -1.0f;
    private static final double MAX_DELTA_X = 1.0f;
    private static final double MIN_DELTA_Y = -1.0f;
    private static final double MAX_DELTA_Y = 1.0f;

    private final double origX1;
    private final double origY1;
    private final double origX2;
    private final double origY2;
    private double deltaX;
    private double deltaY;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private Sprite sprite;

    public Ball (int x1, int y1, int x2, int y2, Sprite sprite)
    {
        this.origX1 = this.x1 = x1;
        this.origY1 = this.y1 = y1;
        this.origX2 = this.x2 = x2;
        this.origY2 = this.y2 = y2;
        this.sprite = sprite;
    }

    public void render (Graphics g)
    {
        sprite.draw (g,
            (int) Math.round (x1),
            (int) Math.round (y1),
            (int) Math.round (x2),
            (int) Math.round (y2));
    }

    public void update (long elapsed)
    {
        double dx = deltaX * elapsed;
        double dy = deltaY * elapsed;
        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;
    }

    public void reset ()
    {
        x1 = origX1;
        y1 = origY1;
        x2 = origX2;
        y2 = origY2;
        deltaX = random (MIN_DELTA_X / 2.0, MAX_DELTA_X / 2.0);
        deltaY = MAX_DELTA_Y / 2.0;
    }

    public void reverseX ()
    {
        deltaX *= -1.0;
    }

    public void setDeltaX (double dx)
    {
        this.deltaX = clamp (dx, MIN_DELTA_X, MAX_DELTA_X);
    }

    public double getDeltaX ()
    {
        return deltaX;
    }

    public void reverseY ()
    {
        this.deltaY *= -1.0;
    }

    public void setDeltaY (double dy)
    {
        this.deltaY = clamp (dy, MIN_DELTA_Y, MAX_DELTA_X);
    }

    public double getDeltaY ()
    {
        return deltaY;
    }
}
