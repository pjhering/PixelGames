package breakout;

import java.awt.Graphics;
import pixel.Sprite;
import static pixel.Utility.clamp;
import static pixel.Utility.random;

public class Ball extends Entity
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
    private double prevX1;
    private double prevY1;
    private double prevX2;
    private double prevY2;

    public Ball (int x1, int y1, int x2, int y2, Sprite sprite)
    {
        super (x1, y1, x2, y2, sprite);
        this.origX1 = x1;
        this.origY1 = y1;
        this.origX2 = x2;
        this.origY2 = y2;
    }

    public void bounce (boolean horizontal, boolean vertical)
    {
        if (horizontal)
        {
            deltaX = deltaX * -1.0;
            x1 = prevX1;
            x2 = prevX2;
        }
        if (vertical)
        {
            deltaY = deltaY * -1.0;
            y1 = prevY1;
            y2 = prevY2;
        }
    }

    public void update (long elapsed)
    {
        prevY1 = y1;
        prevX1 = x1;
        prevY2 = y2;
        prevX2 = x2;

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
}
