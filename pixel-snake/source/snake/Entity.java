package snake;

import java.awt.Graphics;
import pixel.Sprite;

public class Entity
{
    public int x;
    public int y;
    public int width;
    public int height;
    public Sprite sprite;

    public Entity (int x, int y, int width, int height, Sprite sprite)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public void draw (Graphics g)
    {
        sprite.draw (g, x, y, x + width, y + height);
    }

    public void moveTo (Entity that)
    {
        this.x = that.x;
        this.y = that.y;
    }

    public boolean hits (Entity that)
    {
        return this.x < that.x + that.width
            && this.x + this.width > that.x
            && this.y < that.y + that.height
            && this.y + this.height > that.y;
    }
}
