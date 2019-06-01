package pixel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import static java.util.Objects.requireNonNull;

public class AnimatedSprite implements Sprite
{
    private final BufferedImage image;
    private final int length;
    private final int[] sx1;
    private final int[] sy1;
    private final int[] sx2;
    private final int[] sy2;
    private final int width;
    private final int height;
    private final long millisPerFrame;
    private long startTime;
	private boolean playedOnce;

    public AnimatedSprite (BufferedImage image, int x, int y, int width, int height, int rows, int cols, long msPerFrame)
    {
        this.image = requireNonNull (image);
        this.millisPerFrame = msPerFrame;
        this.length = rows * cols;
        this.sx1 = new int[length];
        this.sy1 = new int[length];
        this.sx2 = new int[length];
        this.sy2 = new int[length];

        this.width = width / cols;
        this.height = height / rows;
        int i = 0;

        for (int r = 0; r < rows; r++)
        {
            int yy1 = y + r * this.height;
            int yy2 = yy1 + this.height;

            for (int c = 0; c < cols; c++)
            {
                sy1[i] = yy1;
                sy2[i] = yy2;
                sx1[i] = x + c * this.width;
                sx2[i] = sx1[i] + this.width;
                i += 1;
            }
        }

        startTime = System.currentTimeMillis ();
		playedOnce = false;
    }

    private AnimatedSprite (AnimatedSprite that)
    {
        this.image = that.image;
        this.length = that.length;
        this.sx1 = Arrays.copyOf (that.sx1, length);
        this.sy1 = Arrays.copyOf (that.sy1, length);
        this.sx2 = Arrays.copyOf (that.sx2, length);
        this.sy2 = Arrays.copyOf (that.sy2, length);
        this.width = that.width;
        this.height = that.height;
        this.millisPerFrame = that.millisPerFrame;
        this.startTime = that.startTime;
        this.playedOnce = that.playedOnce;
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
        long now = System.currentTimeMillis ();
        int i = (int) ((now - startTime) / millisPerFrame);
		if (i >= length)
		{
			i %= length;
			playedOnce = true;
		}

        g.drawImage (image, dx1, dy1, dx2, dy2, sx1[i], sy1[i], sx2[i], sy2[i], null);
    }

    public void reset ()
    {
        startTime = System.currentTimeMillis ();
		playedOnce = false;
    }

    public Sprite duplicate ()
    {
        return new AnimatedSprite (this);
    }
}
