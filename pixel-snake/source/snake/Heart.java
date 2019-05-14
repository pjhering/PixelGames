package snake;

import java.util.Random;
import pixel.Sprite;

public class Heart extends Entity
{
    private final Random rand;
    public Heart (int x, int y, int width, int height, Sprite sprite)
    {
        super (x, y, width, height, sprite);
        rand = new Random ();
    }

    public void randomize (int r, int c)
    {
        x = rand.nextInt (r) * width;
        y = rand.nextInt (c) * height;
    }
}
