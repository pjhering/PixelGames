package breakout;

import java.awt.Graphics;
import pixel.Sprite;

public class Brick extends Entity
{
    public boolean hit;

    public Brick (Sprite sprite, int x1, int y1, int x2, int y2)
    {
        super (x1, y1, x2, y2, sprite);
        this.hit = false;
    }

    public void setHit (boolean value) { this.hit = value; }
    public boolean isHit () { return hit; }
}
