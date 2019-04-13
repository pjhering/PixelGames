package breakout;

import java.awt.Graphics;
import pixel.Sprite;

public class Paddle
{
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public Sprite sprite;
    private int halfWidth;

    public Paddle (int x1, int y1, int x2, int y2, Sprite sprite)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.sprite = sprite;
        this.halfWidth = (x2 - x1) / 2;
    }

    public void update (int mouseX)
    {
        x1 = mouseX - halfWidth;
        x2 = mouseX + halfWidth;
    }

    public void render (Graphics g)
    {
        sprite.draw (g, x1, y1, x2, y2);
    }
}
