package pixel.maps;

pubic class LevelFile
{
	private String tilesetImageFile;
	private int tileSize;

	public LevelFile ()
	{
	}

	public String getTilesetImageFile ()
	{
		return tilesetImageFile;
	}

	public void setTilesetImageFile (String value)
	{
		this.tilesetImageFile = value;
	}

	public int getTileSize ()
	{
		return tileSize;
	}

	public void setTileSize (int value)
	{
		this.tileSize = value;
	}
}
