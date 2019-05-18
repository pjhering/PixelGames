package dragontale;

import pixel.Assets;
import pixel.Logger;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap
{
    private double x, y;
    private int xmin, ymin, xmax, ymax;
    private double tween;
    private int[][] map;
    private int tileSize;
    private int numRows, numCols;
    private int width, height;
    private int numTilesAcross;
    private BufferedImage tileSet;
    private Tile[][] tiles;
    private int rowOffset, colOffset;
    private int numRowsToDraw, numColsToDraw;

    public TileMap (int tileSize)
    {
        this.tileSize = tileSize;
        numRowsToDraw = 8;
        numColsToDraw = 11;
        tween = 0.07;
    }

    public void loadTiles (Assets a)
    {
        tileSet = a.getImage (8);
        numTilesAcross = tileSet.getWidth () / tileSize;
        tiles = new Tile[2][numTilesAcross];

        for (int col = 0; col < numTilesAcross; col++)
        {
            tiles[0][col] = new Tile (a.getSprite(col), Tile.NORMAL);
            tiles[1][col] = new Tile (a.getSprite(col + numTilesAcross), Tile.BLOCKED);
        }
    }

    public void loadMap (Assets a)
    {
        try
        {
            String s = a.getString (0);
            Class clss = getClass ();
            InputStream stream = clss.getResourceAsStream (s);
            InputStreamReader reader = new InputStreamReader (stream);
            BufferedReader buffer = new BufferedReader (reader);

            numCols = Integer.parseInt (buffer.readLine ());
            numRows = Integer.parseInt (buffer.readLine ());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            for (int row = 0; row < numRows; row++)
            {
                String line = buffer.readLine ();
                String[] tokens = line.split ("\\s+");

                for (int col = 0; col < numCols; col++)
                {
                    map[row][col] = Integer.parseInt (tokens[col]);
                }
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException (ex);
        }
    }

    public int getType (int row, int col)
    {
        int rc = map[row][col];
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;
        return tiles[r][c].getType ();
    }

    public void setPosition (double x, double y)
    {
        this.x = x;
        this.y = y;

        if (x < xmin) x = xmin;
        if (y < ymin) y = ymin;
        if (x > xmax) x = xmax;
        if (y > ymax) y = ymax;

        colOffset = (int) -this.x / tileSize;
        rowOffset = (int) -this.y / tileSize;
    }

    public void draw (Graphics g)
    {
        for (int row = rowOffset; row <  rowOffset + numRowsToDraw; row++)
        {
            if (row >= numRows) break;

            for (int col = colOffset; col < colOffset + numColsToDraw; col++)
            {
                if (col >= numCols) break;

                if (map[row][col] == 0) continue;

                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;
				int x1 = (int) x + col * tileSize;
				int y1 = (int) y + row * tileSize;
				int x2 = x1 + tileSize;
				int y2 = y1 + tileSize;
				tiles[r][c].getSprite ().draw (g, x1, y1, x2, y2);
            }
        }
    }

    public int getTileSize ()
    {
        return tileSize;
    }

    public int getX ()
    {
        return (int) x;
    }

    public int getY ()
    {
        return (int) y;
    }

    public int getWidth ()
    {
        return width;
    }

    public int getHeight ()
    {
        return height;
    }
}
