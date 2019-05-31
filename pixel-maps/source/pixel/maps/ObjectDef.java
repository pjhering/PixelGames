package pixel.maps;

import java.util.Map;

public class ObjectDef
{
	private String name;
	private double[] shape;
	private Map properties;

	public ObjectDef ()
	{
		System.out.println ("pixel.maps.ObjectDef ()");
	}

	public MapObject getMapObject ()
	{
		if (shape != null)
		{
			if (shape.length == 2)
			{
				return new MapObject (name, properties, shape[0], shape[1]);
			}
			else if (shape.length == 3)
			{
				return new MapObject (name, properties, shape[0], shape[1], shape[2]);
			}
			else if (shape.length == 4)
			{
				return new MapObject (name, properties, shape[0], shape[1], shape[2], shape[3]);
			}
		}

		return null;
	}

	public String getName ()
	{
		return name;
	}

	public void setName (String value)
	{
		this.name = value;
	}

	public double[] getShape ()
	{
		return shape;
	}

	public void setShape (double[] value)
	{
		this.shape = value;
	}

	public Map getProperties ()
	{
		return properties;
	}

	public void setProperties (Map value)
	{
		this.properties = value;
	}
}
