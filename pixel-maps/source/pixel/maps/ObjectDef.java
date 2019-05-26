package pixel.maps;

import java.util.Map;

public class ObjectDef
{
	private double[] shape;
	private Map<String, Object> properties;

	public ObjectDef ()
	{
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
