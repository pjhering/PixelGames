package run2right;

import java.awt.Graphics;
import java.util.List;
import pixel.maps.TileMap;

public class Camera
{
	private final int viewWidth;
	private final int viewHeight;

	public Camera (int viewWidth, int viewHeight)
	{
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
	}

	public void update (TileMap map, Actor actor)
	{
	}

	public void render (Graphics g, TileMap map)
	{
	}

	public void render (Graphics g, Actor a)
	{
	}

	public void render (Graphics g, List<? extends Actor> list)
	{
	}
}
