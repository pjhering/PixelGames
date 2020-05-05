package pixel.ecs;
import java.util.List;

public class QueryNoneOf implements Query
{
    private final Class<? extends Component>[] args;

    public QueryNoneOf (Class<? extends Component> ... args)
    {
        this.args = args;
    }

    public List<Entity> getResults (EntityManager mgr)
    {
        return mgr.noneOf (args);
    }
}
