package pixel;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.net.URL;
import java.util.Map;

public final class Utility
{
    private Utility ()
    {
        // not used; static methods only
    }

    public static URL url (String path)
    {
        return Utility.class.getResource (path);
    }

    public static boolean isRange (double value, double min, double max)
    {
        return value >= min (min, max) && value <= max (min, max);
    }

    public static double random (double min, double max)
    {
        return min + (Math.random () * (max - min));
    }

    public static double clamp (double value, double lower, double upper)
    {
        if (value < min(lower, upper)) return min(lower, upper);

        if (value > max(lower, upper)) return max(lower, upper);

        return value;
    }

    public static int clamp (int value, int lower, int upper)
    {
        if (value < min(lower, upper)) return min(lower, upper);

        if (value > max(lower, upper)) return max(lower, upper);

        return value;
    }

    public static void debug (Object ... objs)
    {
        System.out.print ("debug> ");
        for (Object o : objs)
        {
            System.out.print (o);
            System.out.print (' ');
        }
        System.out.println ();
        System.out.flush ();
    }
}
