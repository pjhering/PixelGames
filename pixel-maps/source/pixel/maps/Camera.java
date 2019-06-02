package pixel.maps;

import java.awt.Graphics;

public class Camera
{
    private final Viewport viewport;

    public Camera (int viewportWidth, int viewportHeight)
    {
        this.viewport = new Viewport (viewportWidth, viewportHeight);
    }

    public Viewport getViewport ()
    {
        return viewport;
    }

    public void render (Graphics g, TileMap map)
    {
        int xOffset = viewport.getX1 ();
        int yOffset = viewport.getY1 ();
        int startColumn = map.getColumnForPixel (xOffset);
        int endColumn = map.getColumnForPixel (viewport.getX2 ());
        int startRow = map.getRowForPixel (yOffset);
        int endRow = map.getRowForPixel (viewport.getY2 ());
		//
        // System.out.printf ("offsets: %d, %d; columns: %d, %d; rows: %d, %d\n", xOffset, yOffset, startColumn, endColumn, startRow, endRow);
		//
        map.draw (g, xOffset, yOffset, startRow, endRow, startColumn, endColumn);
    }
}
