package run2right;

import java.awt.Graphics;
import java.util.List;
import pixel.maps.TileMap;

public class Camera
{
	private final int viewWidth;
	private final int viewHeight;
	private final Viewport viewport;

	public Camera (int viewWidth, int viewHeight)
	{
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		this.viewport = new Viewport (viewWidth, viewHeight);
	}

	public void update (TileMap map, Actor actor)
	{
		viewport.update (map, actor);
	}

	public void render (Graphics g, TileMap map)
	{
		int startColumn = map.getColumnForPixel (viewport.getX1 ());
		int endColumn = map.getColumnForPixel (viewport.getX2 ());
		int startRow = map.getRowForPixel (viewport.getY1 ());
		int endRow = map.getRowForPixel (viewport.getY2 ());

		map.draw (
			g,
			viewport.getXOffset (),
			viewport.getYOffset (),
			startRow,
			endRow,
			startColumn,
			endColumn);
	}

	public void render (Graphics g, Actor a)
	{
		a.draw (g, viewport.getXOffset (), viewport.getYOffset ());
	}

	public void render (Graphics g, List<? extends Actor> list)
	{
		list.forEach (a -> render (g, a));
	}
}
