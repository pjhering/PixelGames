package pixel.maps;

import java.util.HashMap;
import java.util.Map;

public class MapObject
{
	public static enum Type { CIRCLE, RECTANGLE }

	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected double radius;
	protected Type type;
	protected Map<String, Object> properties;

	public MapObject (double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = Type.RECTANGLE;
		this.properties = new HashMap<> ();
	}

	public MapObject (double x, double y, double radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.type = Type.CIRCLE;
		this.properties = new HashMap<> ();
	}
}
