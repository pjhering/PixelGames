package pixel;

import java.util.Map;

public final class Utility
{
    private Utility ()
    {
        // not used; static methods only
    }

    public static double random (double min, double max)
    {
        return min + (Math.random () * (max - min));
    }

    public static double clamp (double value, double lower, double upper)
    {
        if (value < lower) return lower;

        if (value > upper) return upper;

        return value;
    }

    public static int clamp (int value, int lower, int upper)
    {
        if (value < lower) return lower;

        if (value > upper) return upper;

        return value;
    }

    public static final boolean DEBUG =
        (System.getProperty ("DEBUG") != null
        && System.getProperty ("DEBUG").equals ("on"));

    public static void debug (Object ... objs)
    {
        if (DEBUG)
        {
            for (Object o : objs)
            {
                System.out.print (">>");
                System.out.println (o);
            }
        }
    }
}
