package pixel.ecs;
import java.util.List;

public class QueryRemoveAll implements Query
{
    private final Query q1;
    private final Query q2;

    public QueryRemoveAll (Query q1, Query q2)
    {
        this.q1 = q1;
        this.q2 = q2;
    }

    public List<Entity> getResults (EntityManager mgr)
    {
        List<Entity> l1 = q1.getResults (mgr);
        List<Entity> l2 = q2.getResults (mgr);
        l1.removeAll (l2);

        return l1;
    }
}
