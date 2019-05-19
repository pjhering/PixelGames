package pixel.maps;

public class TileLayer
{
	public final int tileWidth;
	public final int tileHeight;
	public final int rows;
	public final int columns;
	private final Tile[] tileset;
	private final int[][] map;

	public TileLayer (int tileWidth, int tileHeight, int rows,  int columns, Tile[] tileset)
	{
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.rows = rows;
		this.columns = columns;
		this.tileset = tileset;
		this.map = new int[columns][rows];
	}

	public Tile getTile (double x, double y)
	{
		int c = (int)x / tileWidth;
		int r = (int)y / tileHeight;
		int i = map[c][r];
		return tileset[i];
	}

	public int getLayerWidth ()
	{
		return columns * tileWidth;
	}

	public int getLayerHeight ()
	{
		return rows * tileHeight;
	}
}
