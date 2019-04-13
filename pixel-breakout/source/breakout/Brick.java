package breakout;

import java.awt.Graphics;
import pixel.Sprite;

public class Brick
{
    public final Sprite sprite;
    public final int x1;
    public final int y1;
    public final int x2;
    public final int y2;
    public boolean hit;

    public Brick (Sprite sprite, int x1, int y1, int x2, int y2)
    {
        this.sprite = sprite;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.hit = false;
    }

    public void render (Graphics g)
    {
        sprite.draw (g, x1, y1, x2, y2);
    }
}
