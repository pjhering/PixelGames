package pixel.maps;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ObjectLayer implements Iterable<MapObject>
{
	private final Set<MapObject> active;
    private final Set<MapObject> toBeAdded;
    private final Set<MapObject> toBeRemoved;
    private final MapObjectObserver observer;

    public ObjectLayer ()
    {
        this.active = new HashSet<> ();
        this.toBeAdded = new HashSet<> ();
        this.toBeRemoved = new HashSet<> ();
    }

    public ObjectLayer (MapObjectObserver observer)
    {
        this ();
        this.observer = observer;
    }

    @Override
    public Iterator<MapObject> iterator ()
    {
        return active;
    }

    public void add (MapObject obj)
    {
        toBeAdded.add (obj);
    }

    public void remove (MapObject obj)
    {
        toBeRemoved.add (obj);
    }

    public void update (long elapsedMillis)
    {
        if (!toBeRemoved.empty ())
        {
            active.removeAll (toBeRemoved);
            toBeRemoved.clear ();
        }

        if (!toBeAdded.empty ())
        {
            active.addAll (toBeAdded);
            toBeAdded.clear ();
        }

        for (MapObject obj : active)
        {
            obj.update (elapsedMillis);
        }
    }
}
