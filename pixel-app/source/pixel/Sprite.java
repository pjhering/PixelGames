package pixel;

import java.awt.Graphics;

public interface Sprite
{
    public int getWidth ();

    public int getHeight ();

    public void draw (Graphics g, int dx1, int dy1, int dx2, int dy2);

    public void reset ();

    public Sprite duplicate ();
}
