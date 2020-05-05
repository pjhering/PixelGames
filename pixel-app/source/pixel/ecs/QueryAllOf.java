package pixel.ecs;
import java.util.List;

public class QueryAllOf implements Query
{
    private final Class<? extends Component>[] args;

    public QueryAllOf (Class<? extends Component> ... args)
    {
        this.args = args;
    }

    public List<Entity> getResults (EntityManager mgr)
    {
        return mgr.allOf (args);
    }
}
