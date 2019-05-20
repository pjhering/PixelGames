package pixel.maps;

pubilc class TileMap
{
    private Background background;
    private TileLayer tileLayer;
    private List<ObjectLayer> objectLayers;

    public TileMap ()
    {
    }

    public void update (long elapsedMillis)
    {
    }

    public void draw (Graphics g)
    {
    }

    public Background getBackground ()
    {
        return background;
    }

    public void setBackground (Background value)
    {
        this.background = value;
    }

    public TileLayer getTileLayer ()
    {
        return tileLayer;
    }

    public void setTileLayer (TileLayer value)
    {
        this.tileLayer = value;
    }

    public List<ObjectLayer> getObjectLayers ()
    {
        return objectLayers;
    }

    public void setObjectLayers (List<ObjectLayer> value)
    {
        this.objectLayers = value;
    }
}
