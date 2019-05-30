package pixel.ces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

@SuppressWarnings("unchecked")
public class EntityManager
{
    private int componentClassCount;
    private List<Component[]> components;
    private List<DefaultEntity> entities;
    private Stack<Integer> unused;
    private Map<Class<? extends Component>, Integer> componentClassIdMap;
    private int size;

    public EntityManager (int initialSize, Class<? extends Component> ... classes)
    {
        componentClassCount = classes.length;
        components = new ArrayList<> (initialSize);
        entities = new ArrayList<> (initialSize);
        unused = new Stack<> ();
        componentClassIdMap = new HashMap<> ();
        size = initialSize;

        for (int i = 0; i < initialSize; i++)
        {
            components.add (i, new Component[componentClassCount]);
            entities.add (i, new DefaultEntity (i, this));
            unused.push (i);
        }

        for (int i = 0; i < classes.length; i++)
        {
            componentClassIdMap.put (classes[i], i);
        }
    }

    <C extends Component> C get (int entity, Class<C> c)
    {
        if (0 <= entity && entity < components.size ())
        {
            Component[] array = components.get (entity);

            Integer cid = componentClassIdMap.get (c);

            if (cid != null && 0 <= cid && cid < array.length)
            {
                return (C) array[cid];
            }
        }

        return null;
    }

    public <C extends Component> Entity create (C ... comps)
    {
        if (unused.isEmpty ())
        {
            for (int i = 0; i < 10; i++)
            {
                int eid = i + size;
                components.add (eid, new Component[componentClassCount]);
                entities.add (eid, new DefaultEntity (eid, this));
                unused.push (eid);
            }

            size += 10;
        }

        int eid = unused.pop ();
        Component[] array = components.get (eid);
        Entity e = entities.get (eid);

        for (Component c : comps)
        {
            int cid = componentClassIdMap.get (c.getClass ());
            array[cid] = c;
        }

        return e;
    }

    public <C extends Component> void add (Entity e, C ... comps)
    {
        int eid = ((DefaultEntity)e).id;
        Component[] array = components.get (eid);

        for (Component c : comps)
        {
            int cid = componentClassIdMap.get (c.getClass ());
            array[cid] = c;
        }
    }

    public <C extends Component> void remove (Entity e, C ... comps)
    {
        int eid = ((DefaultEntity)e).id;
        Component[] array = components.get (eid);

        for (Component c : comps)
        {
            int cid = componentClassIdMap.get (c.getClass ());
            array[cid] = null;
        }
    }

    public void destroy (Entity e)
    {
        int eid = ((DefaultEntity)e).id;
        Component[] array = components.get (eid);

        for (int i = 0; i < array.length; i++)
        {
            array[i] = null;
        }

        unused.push (eid);
    }

    public Collection<Entity> find (Class<? extends Component> ... classes)
    {
        List<Entity> list = new ArrayList<> ();
        List<Integer> cids = new ArrayList<> ();

        for (Class<? extends Component> c : classes)
        {
            int cid = componentClassIdMap.get (c);
            cids.add (cid);
        }

        OUTER:
        for (int eid = 0; eid < components.size (); eid++)
        {
            Component[] array = components.get (eid);

            for (int cid : cids)
            {
                if (array[cid] == null)
                {
                    continue OUTER;
                }
            }

            list.add (entities.get (eid));
        }

        return list;
    }
}
