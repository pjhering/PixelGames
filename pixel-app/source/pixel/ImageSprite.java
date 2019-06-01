package pixel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.util.Objects.requireNonNull;

public class ImageSprite implements Sprite
{
    private final BufferedImage image;
    private final int sx1;
    private final int sy1;
    private final int sx2;
    private final int sy2;
    private final int width;
    private final int height;

    public ImageSprite (BufferedImage image, int sx1, int sy1, int sx2, int sy2)
    {
        this.image = requireNonNull (image);
        this.sx1 = sx1;
        this.sy1 = sy1;
        this.sx2 = sx2;
        this.sy2 = sy2;
        this.width = sx2 - sx1;
        this.height = sy2 - sy1;
    }

    public ImageSprite (BufferedImage image)
    {
        this (image, 0, 0, image.getWidth (), image.getHeight ());
    }

    private ImageSprite (ImageSprite that)
    {
        this.image = that.image;
        this.sx1 = that.sx1;
        this.sy1 = that.sy1;
        this.sx2 = that.sx2;
        this.sy2 = that.sy2;
        this.width = that.width;
        this.height = that.height;
    }

    public int getWidth ()
    {
        return width;
    }

    public int getHeight ()
    {
        return this.height;
    }

    public void draw (Graphics g, int dx1, int dy1, int dx2, int dy2)
    {
        g.drawImage (image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
    }

    public void reset ()
    {
    }

    public Sprite duplicate ()
    {
        return new ImageSprite (this);
    }
}
