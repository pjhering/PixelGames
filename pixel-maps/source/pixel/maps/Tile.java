package pixel.maps;

import pixel.Sprite;

public class Tile
{
	private Sprite sprite;
	private boolean blocked;

	public Tile (Sprite sprite, boolean blocked)
	{
		this.sprite = sprite;
		this.blocked = blocked;
	}

	public Sprite getSprite ()
	{
		return sprite;
	}

	public void setSprite (Sprite value)
	{
		this.sprite = value;
	}

	public boolean isBlocked ()
	{
		return blocked;
	}

	public void setBlocked (boolean value)
	{
		this.blocked = value;
	}
}
