package pixel.maps;

import java.util.HashMap;
import java.util.Map;

public class MapObject
{
	public static enum Type { POINT, CIRCLE, RECTANGLE }

	protected double x = Double.NaN;
	protected double y = Double.NaN;
	protected double width = Double.NaN;
	protected double height = Double.NaN;
	protected double radius = Double.NaN;
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

	public static boolean hits (MapObject a, MapObject b)
	{
		switch (a.getType ())
		{
			case POINT:
				switch (b.getType ())
				{
					case POINT:
						return pointHitsPoint (a, b);
					case CIRCLE:
						return pointHitsCircle (a, b);
					case RECTANGLE:
						return pointHitsRectangle (a, b);
				}
			case CIRCLE:
				switch (b.getType ())
				{
					case POINT:
						return pointHitsPoint (b, a);
					case CIRCLE:
						return circleHitsCircle (a, b);
					case RECTANGLE:
						return circleHitsRectangle (a, b);
				}
			case RECTANGLE:
				switch (b.getType ())
				{
					case POINT:
						return pointHitsRectangle (b, a);
					case CIRCLE:
						return circleHitsRectangle (b, a);
					case RECTANGLE:
						return rectangleHitsRectangle (a, b);
				}
		}

		return false;
	}

	private static boolean pointHitsPoint (MapObject a, MapObject b)
	{
		return a.x == b.x && a.y == b.y;
	}

	private static double distSqr (MapObject a, MapObject b)
	{
		double x = a.x - b.x;
		double y = a.y - b.y;
		return x * x + y * y;
	}

	private static boolean pointHitsCircle (MapObject a, MapObject b)
	{
		double d = distSqr (a, b);
		return d < b.radius * b.radius;
	}

	private static boolean pointHitsRectangle (MapObject a, MapObject b)
	{
		return a.x > b.x
			&& a.x < b.x + b.width
			&& a.y > b.y
			&& a.y < b.y + b.height;
	}

	private static boolean circleHitsCircle (MapObject a, MapObject b)
	{
		double d = distSqr (a, b);
		double r = a.radius + b.radius;
		return d < r * r;
	}

	private static boolean circleHitsRectangle (MapObject c, MapObject r)
	{
		double testX = c.x;
		double testY = c.y;

		if (c.x < r.x)                testX = r.x;
		else if (c.x > r.x + r.width) testX = r.x + r.width;

		if (c.y < r.y)                testY = r.y;
		else if (c.y > r.y + r.height) testY = r.y + r.height;

		double distX = c.x - testX;
		double distY = c.y - testY;
		double distance = Math.sqrt((distX * distX) + (distY * distY));

		if (distance <= c.radius)
		{
	       return true;
		}
	    return false;
	}

	private static boolean rectangleHitsRectangle (MapObject a, MapObject b)
	{
		return a.x < b.x + b.width
			&& a.x + a.width > b.x
			&& a.y < b.y + b.height
			&& a.y + a.height > b.y;
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
