package pixel.maps;

import java.util.Map;

public class ObjectDef
{
	private double[] shape;
	private Map<String, Object> properties;

	public ObjectDef ()
	{
	}

	public MapObject getMapObject ()
	{
		if (shape != null)
		{
			if (shape.length == 2)
			{
				return new MapObject (properties, shape[0], shape[1], shape[2]);
			}
			else if (shape.length == 3)
			{
				return new MapObject (properties, shape[0], shape[1], shape[2]);
			}
			else if (shape.length == 4)
			{
				return new MapObject (properties, shape[0], shape[1], shape[2], shape[3]);
			}
		}

		return null;
	}

	public double[] getShape ()
	{
		return shape;
	}

	public void setShape (double[] value)
	{
		this.shape = value;
	}

	public Map<String, Object> getProperties ()
	{
		return properties;
	}

	public void setProperties (Map<String, Object> value)
	{
		this.properties = value;
	}
}
