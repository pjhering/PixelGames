package pixel;

public class Logger
{
    private final String TAG;

    public Logger (Class c)
    {
        this.TAG = c.getSimpleName ();
    }

    public void print (Object message)
    {
        System.out.printf ("%s - %s\n", TAG, message);
    }
}
