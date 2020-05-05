package pixel.ecs;
import java.util.List;

public class QueryOnlyOneOf implements Query
{
    private final Class<? extends Component>[] args;

    public QueryOnlyOneOf (Class<? extends Component> ... args)
    {
        this.args = args;
    }

    public List<Entity> getResults (EntityManager mgr)
    {
        return mgr.onlyOneOf (args);
    }
}
