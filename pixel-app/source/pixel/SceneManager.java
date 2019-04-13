package pixel;

public interface SceneManager
{
    public void push (Scene scene);

    public Scene pop ();

    public Scene peek ();

    public boolean replace (Scene scene);
}
