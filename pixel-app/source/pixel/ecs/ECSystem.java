package pixel.ecs;
import java.awt.Graphics;
import java.util.List;

public abstract class ECSystem
{
    protected EntityManager mgr;
    protected Query query;

    public ECSystem (EntityManager mgr, Query query)
    {
        this.mgr = mgr;
        this.query = query;
    }

    final void update (long elasped)
    {
        update (query.getResults (mgr), elasped);
    }

    final void render (Graphics g)
    {
        render (query.getResults (mgr), g);
    }

    public abstract void update (List<Entity> list, long elasped);

    public abstract void render (List<Entity> list, Graphics g);
}
