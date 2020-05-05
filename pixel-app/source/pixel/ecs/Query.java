package pixel.ecs;
import java.util.List;

public interface Query
{
    public List<Entity> getResults (EntityManager mgr);
}
