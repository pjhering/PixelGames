package breakout;

public  class BrickManager
{
    private Brick[] bricks;

    public BrickManager (Brick[] bricks)
    {
        this.bricks = bricks;
    }

    public int countHit ()
    {
        int count = 0;

        for (Brick b : bricks)
        {
            if (b.isHit ())
            {
                count = count + 1;
            }
        }

        return count;
    }
}
