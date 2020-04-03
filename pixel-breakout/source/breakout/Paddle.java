package breakout;

import java.awt.Graphics;
import pixel.Sprite;

public class Paddle extends Entity
{
    private double halfWidth;

    public Paddle (int x1, int y1, int x2, int y2, Sprite sprite)
    {
        super (x1, y1, x2, y2, sprite);
        this.halfWidth = (x2 - x1) / 2;
    }

    public void update (int mouseX)
    {
        x1 = mouseX - halfWidth;
        x2 = mouseX + halfWidth;
    }
}
