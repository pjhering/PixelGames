package pixel.ecs;
import java.util.ArrayList;
import java.util.List;

public class EntityManager
{
    private int entityCount = 100;
    private List<Class<? extends Component>> classList = new ArrayList<> ();
    private Component[][] table;
    private List<EntityImpl> entities;

    private EntityManager ()
    {
    }

    public int indexOf (Class<? extends Component> c)
    {
        int i = classList.indexOf (c);

        if (i < 0) throw new RuntimeException ("unregistered component class: " + c.getName ());

        return i;
    }

    public List<Entity> allOf (Class<? extends Component> ... args)
    {
        int[] cids = new int[args.length];
        for (int i  = 0; i < args.length; i++)
        {
            cids[i] = indexOf (args[i]);
        }

        List<Entity> list = new ArrayList<> ();
        for (EntityImpl e : entities)
        {
            if (!e.inUse) continue; // don't consider released entities

            boolean match = true;

            for (int i = 0; i < cids.length; i++)
            {
                if (table[e.ID][cids[i]] == null)
                {
                    match = false;
                    break;
                }
            }

            if (match)
            {
                list.add (e);
            }
        }

        return list;
    }

    public List<Entity> anyOf (Class<? extends Component> ... args)
    {
        int[] cids = new int[args.length];
        for (int i  = 0; i < args.length; i++)
        {
            cids[i] = indexOf (args[i]);
        }

        List<Entity> list = new ArrayList<> ();
        for (EntityImpl e : entities)
        {
            if (!e.inUse) continue; // don't consider released entities

            boolean match = false;

            for (int i = 0; i < cids.length; i++)
            {
                if (table[e.ID][cids[i]] != null)
                {
                    match = true;
                    break;
                }
            }

            if (match)
            {
                list.add (e);
            }
        }

        return list;
    }

    public List<Entity> noneOf (Class<? extends Component> ... args)
    {
        int[] cids = new int[args.length];
        for (int i  = 0; i < args.length; i++)
        {
            cids[i] = indexOf (args[i]);
        }

        List<Entity> list = new ArrayList<> ();
        for (EntityImpl e : entities)
        {
            if (!e.inUse) continue; // don't consider released entities

            boolean match = true;

            for (int i = 0; i < cids.length; i++)
            {
                if (table[e.ID][cids[i]] != null)
                {
                    match = false;
                    break;
                }
            }

            if (match)
            {
                list.add (e);
            }
        }

        return list;
    }

    public List<Entity> onlyOneOf (Class<? extends Component> ... args)
    {
        int[] cids = new int[args.length];
        for (int i  = 0; i < args.length; i++)
        {
            cids[i] = indexOf (args[i]);
        }

        List<Entity> list = new ArrayList<> ();
        for (EntityImpl e : entities)
        {
            if (!e.inUse) continue; // don't consider released entities

            int count = 0;

            for (int i = 0; i < cids.length; i++)
            {
                if (table[e.ID][cids[i]] != null)
                {
                    count += 1;
                }
            }

            if (count == 1)
            {
                list.add (e);
            }
        }

        return list;
    }

    public <C extends Component> Entity obtain (C ... args)
    {
        EntityImpl impl = null;

        for (EntityImpl e : entities)
        {
            if (!e.inUse)
            {
                impl = e;
                break;
            }
        }

        if (impl == null)
        {
            Component[][] temp = table;
            table = new Component[entityCount + 100][classList.size ()];
            System.arraycopy (temp, 0, table, 0, table.length);

            for (int i = 0; i < 100; i++)
            {
                EntityImpl e = new EntityImpl (entityCount + i, this);
                entities.add (e);
            }

            impl = entities.get (entityCount);
            entityCount += 100;
        }

        for (C comp : args)
        {
            int i = indexOf (comp.getClass ());
            table[impl.ID][i] = comp;
        }

        impl.inUse = true;
        return impl;
    }

    public void release (Entity e)
    {
        EntityImpl impl = (EntityImpl)e;
        impl.inUse = false;
        clear (impl);
    }

    private void clear (EntityImpl e)
    {
        for (int i = 0; i < table[e.ID].length; i++)
        {
            table[e.ID][i] = null;
        }
    }

    private <C extends Component> C get (Entity e, Class<C> c)
    {
        EntityImpl impl = (EntityImpl)e;
        if (!impl.inUse) throw new RuntimeException ("entity#" + impl.ID +" has been released");

        int i = indexOf (c);
        return (C) table[impl.ID][i];
    }

    private <C extends Component> void set (Entity e, C c)
    {
        EntityImpl impl = (EntityImpl)e;
        if (!impl.inUse) throw new RuntimeException ("entity#" + impl.ID +" has been released");

        int i = indexOf (c.getClass ());
        table[impl.ID][i] = c;
    }

    private class EntityImpl implements Entity
    {
        final int ID;
        boolean inUse = false;
        final EntityManager mgr;

        public EntityImpl (int id, EntityManager mgr)
        {
            this.ID = id;
            this.mgr = mgr;
        }

        public <C extends Component> C get (Class<C> c)
        {
            return mgr.get (this, c);
        }

        public <C extends Component> void set (C c)
        {
            mgr.set (this, c);
        }
    }

    public static class Builder
    {
        private EntityManager mgr;

        public Builder ()
        {
            mgr = new EntityManager ();
        }

        public Builder register (Class<? extends Component> c)
        {
            if (!mgr.classList.contains (c))
            {
                mgr.classList.add (c);
            }

            return this;
        }

        public EntityManager create ()
        {
            mgr.table = new Component[mgr.entityCount][mgr.classList.size ()];

            mgr.entities = new ArrayList<> ();
            for (int i = 0; i < mgr.entityCount; i++)
            {
                EntityImpl e = mgr.new EntityImpl (i, mgr);
                mgr.entities.add (e);
            }

            return mgr;
        }
    }
}
