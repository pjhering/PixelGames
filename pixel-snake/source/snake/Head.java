package snake;

import pixel.Sprite;

public class Head extends Entity
{
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public int direction;

    public Head (int x, int y, int width, int height, Sprite sprite)
    {
        super (x, y, width, height, sprite);
        direction = RIGHT;
    }

    public void move ()
    {
        switch (direction)
        {
            case UP:
                y -= height;
                if (y < 0) y += Main.HEIGHT;
                break;

            case DOWN:
                y += height;
                if (y >= Main.HEIGHT) y -= Main.HEIGHT;
                break;

            case LEFT:
                x -= width;
                if (x < 0) x += Main.WIDTH;
                break;

            case RIGHT:
                x += width;
                if (x >= Main.WIDTH) x -= Main.WIDTH;
                break;
        }
    }
}
