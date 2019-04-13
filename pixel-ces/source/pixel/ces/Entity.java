package pixel.ces;

public interface Entity
{
    public int id ();
    public <C extends Component> C get (Class<C> c);
}
