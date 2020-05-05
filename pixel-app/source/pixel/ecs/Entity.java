package pixel.ecs;

public interface Entity
{
    public <C extends Component> C get (Class<C> c);

    public <C extends Component> void set (C c);
}
