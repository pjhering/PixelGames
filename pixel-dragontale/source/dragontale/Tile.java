package dragontale;

import pixel.Sprite;

public class Tile
{
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;

	private Sprite sprite;
    private int type;

    public Tile (Sprite sprite, int type)
    {
        this.sprite = sprite;
        this.type = type;
    }

    public Sprite getSprite ()
    {
        return this.sprite;
    }

    public int getType ()
    {
        return  this.type;
    }
}
