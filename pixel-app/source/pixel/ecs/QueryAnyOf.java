package pixel.ecs;
import java.util.List;

public class QueryAnyOf implements Query
{
    private final Class<? extends Component>[] args;

    public QueryAnyOf (Class<? extends Component> ... args)
    {
        this.args = args;
    }

    public List<Entity> getResults (EntityManager mgr)
    {
        return mgr.anyOf (args);
    }
}
