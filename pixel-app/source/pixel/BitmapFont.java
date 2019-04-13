package pixel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.requireNonNull;
import javax.imageio.ImageIO;

public class BitmapFont
{
    private final BufferedImage image;
    private final Map<Character, Glyph> map;

    public BitmapFont (BufferedImage image, Map<Character, Glyph> map)
    {
        this.image = requireNonNull (image);
        this.map = requireNonNull (map);
    }

    public static BitmapFont load (URL url) throws Exception
    {
        try (InputStream stream = url.openStream ())
        {
            InputStreamReader reader = new InputStreamReader (stream);
            BufferedReader buffer = new BufferedReader (reader);

            String line = buffer.readLine ();
            BufferedImage orig = ImageIO.read (BitmapFont.class.getResource (line));
            BufferedImage image = new BufferedImage (orig.getWidth (), orig.getHeight (), TYPE_INT_ARGB);
            Graphics gfx = image.getGraphics ();
            gfx.drawImage (orig, 0, 0, image.getWidth (), image.getHeight (), null);

            Map<Character, Glyph> map = new HashMap<> ();
            while ((line = buffer.readLine ()) != null)
            {
                String[] s = line.split ("\\s+");
                char c = (char) Integer.parseInt (s[0]);
                int x1 = Integer.parseInt (s[1]);
                int y1 = Integer.parseInt (s[2]);
                int x2 = Integer.parseInt (s[3]);
                int y2 = Integer.parseInt (s[4]);
                int descent = Integer.parseInt (s[5]);

                map.put (c, new Glyph (x1, y1, x2, y2, descent));
            }

            return new BitmapFont (image, map);
        }
    }

    public int getWidth (String text)
    {
        int length = text.length ();
        int width = 0;

        for (int i = 0; i < length; i++)
        {
            Glyph g = map.get (text.charAt (i));
            width += g.width;
        }

        return width;
    }

    public int getHeight (String text)
    {
        int length = text.length ();
        int height = Integer.MIN_VALUE;

        for (int i = 0; i < length; i++)
        {
            Glyph g = map.get (text.charAt (i));
            int h = g.ascent + g.descent;
            if (h > height)
            {
                height = h;
            }
        }

        return height;
    }

    public void draw (Graphics gfx, String text, int x, int y)
    {
        int length = text.length ();
        int offset = 0;

        for (int i = 0; i < length; i++)
        {
            Glyph g = map.get (text.charAt (i));

            if (g != null)
            {
                gfx.drawImage (
                    image,
                    x + offset, y - g.ascent, x + offset + g.width, y + g.descent,
                    g.sx1, g.sy1, g.sx2, g.sy2,
                    null);
                offset += g.width;
            }
        }
    }
}
