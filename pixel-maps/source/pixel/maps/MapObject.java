package pixel.maps;

import java.util.HashMap;
import java.util.Map;

public class MapObject
{
	public static enum Type { POINT, CIRCLE, RECTANGLE }

	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected double radius;
	protected Type type;
	protected Map<String, Object> properties;

	public MapObject (Map<String, Object> properties, double x, double y, double width, double height)
	{
		this.properties = properties;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = Type.RECTANGLE;
		this.properties = new HashMap<> ();
	}

	public MapObject (Map<String, Object> properties, double x, double y, double radius)
	{
		this.properties = properties;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.type = Type.CIRCLE;
		this.properties = new HashMap<> ();
	}

	public MapObject (Map<String, Object> properties, double x, double y)
	{
		this.properties = properties;
		this.x = x;
		this.y = y;
		this.type = Type.POINT;
		this.properties = new HashMap<> ();
	}

	public double getX ()
	{
		return x;
	}

	public double getY ()
	{
		return y;
	}

	public double getWidth ()
	{
		return width;
	}

	public double getHeight ()
	{
		return height;
	}

	public double getRadius ()
	{
		return radius;
	}

	public Type getType ()
	{
		return type;
	}

	public Object getProperty (String key)
	{
		return properties.get (key);
	}

	public Object putProperty (String key, Object value)
	{
		return properties.put (key, value);
	}

	public boolean hasProperty (String key)
	{
		return properties.containsKey (key);
	}

	public Object removeProperty (String key)
	{
		return properties.remove (key);
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
