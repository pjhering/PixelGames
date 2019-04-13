package pixel;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private final BufferedImage image;

    public SpriteSheet (BufferedImage image)
    {
        this.image = image;
    }

    public ImageSprite getImageSprite (int x, int y, int width, int height)
    {
        return new ImageSprite (image, x, y, x + width, y + height);
    }

    public AnimatedSprite getAnimatedSprite (int x, int y, int width, int height, int rows, int cols, long msPerFrame)
    {
        return new AnimatedSprite (image, x, y, width, height, rows, cols, msPerFrame);
    }

    public BufferedImage getSubimage (int x, int y, int width, int height)
    {
        return image.getSubimage (x, y, width, height);
    }

    public BufferedImage[] getSubimageArray (int x, int y, int width, int height, int rows, int cols)
    {
        BufferedImage[] array = new BufferedImage[rows * cols];
        int subWidth = width / cols;
        int subHeight = height / rows;
        int i = 0;

        for(int r = 0; r < rows; r++)
        {
            int subY = y + (r * subHeight);

            for (int c = 0; c < cols; c++)
            {
                int subX = x + (c * subHeight);

                array[i] = getSubimage (subX, subY, subWidth, subHeight);
                i += 1;
            }
        }

        return array;
    }
}
