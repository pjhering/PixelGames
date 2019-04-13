package pixel.ces;

class DefaultEntity implements Entity
{
    int id;
    EntityManager mgr;

    DefaultEntity (int id, EntityManager mgr)
    {
        this.id = id;
        this.mgr = mgr;
    }

    public int id ()
    {
        return id;
    }

    public <C extends Component> C get (Class<C> c)
    {
        return mgr.get (id, c);
    }
}
