package pixel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;

public class BitmapFontCreator
{
    public static final String text = "0123456789abcdefghijklmnopqrstuvwxyz" +
        "ABCEDFGHIJKLMNOPQRSTUVWXYZ `~!@#$%^&*()_+-=[]{}\\|;:'\"<,>.?/";
    public static void main(String[] args) throws Exception
    {
        if (args.length == 2)
        {
            create (args[0], args[1]);
        }
    }

    private static void create (String fontDef, String colorDef) throws Exception
    {
        String dirPath = "font/" + fontDef.replaceAll ("-", "/") + "/" + colorDef;
        File dir = new File (dirPath);
        dir.mkdirs ();

        File imageFile = new File (dir, "glyphs.png");
        File glyphFile = new File (dir, "font.txt");
        FileWriter writer = new FileWriter (glyphFile);
        Font font = Font.decode (fontDef);
        Color fgColor = Color.decode (colorDef);
        String[] rows = divideIntoRowsAndColumns (text);

        BufferedImage image = new BufferedImage (1, 1, TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics ();
        FontMetrics fm = g.getFontMetrics (font);
        int leading = fm.getLeading ();
        int ascent = fm.getMaxAscent ();
        int rowHeight = fm.getHeight () + 2;
        int imageHeight = rows.length * rowHeight;
        int imageWidth = rows[0].length () * ((int) Math.ceil (fm.getMaxCharBounds (g).getWidth ()));
        g.dispose ();

        image = new BufferedImage (imageWidth, imageHeight, TYPE_INT_ARGB);
        g = (Graphics2D) image.getGraphics ();
        g.setFont (font);
        fm = g.getFontMetrics ();
        g.setColor (new Color (0,0,0,0));
        g.fillRect (0, 0, imageWidth, imageHeight);
        int cellWidth = imageWidth / rows[0].length ();

        writer.write ("/");
        writer.write (dirPath);
        writer.write ("/");
        writer.write (imageFile.getName ());
        writer.write ('\n');

        int descent = fm.getDescent ();
        for (int i = 0; i < rows.length; i++)
        {
            int y = leading + ascent + (rowHeight * i);
            char[] cs = rows[i].toCharArray ();
            int x = 0;

            for (int j = 0; j < cs.length; j++)
            {
                String cStr = cs[j] + "";
                Rectangle2D box = fm.getStringBounds (cStr, g);
                box.setRect (x, y - (leading + ascent), box.getWidth (), box.getHeight ());
                g.setColor (fgColor);
                g.drawString (cStr, x, y);
                x += cellWidth;

                writer.write (((int) cs[j]) + " ");
                writer.write (((int)box.getMinX ()) + " ");
                writer.write (((int)box.getMinY ()) + " ");
                writer.write ((int)(box.getMaxX ()) + " ");
                writer.write ((int)(box.getMaxY ()) + " ");
                writer.write (descent + "\n");
            }
        }

        writer.flush ();
        writer.close ();

        ImageIO.write (image, "png", imageFile);
    }

    private static String[] divideIntoRowsAndColumns (String text)
    {
        int length = text.length ();

        if (length < 3)
        {
            return new String[]{ text };
        }

        int bestRowSize = 0;
        int bestColSize = 0;
        int smallestDiff = Integer.MAX_VALUE;

        for (int r = 1; r < length; r++)
        {
            for (int c = 1; c < length; c++)
            {
                int size = r * c;

                if (size >= length)
                {
                    int diff = Math.abs (r - c);

                    if (diff < smallestDiff)
                    {
                        smallestDiff = diff;
                        bestRowSize = r;
                        bestColSize = c;
                    }
                }
            }
        }

        String[] array = new String[bestRowSize];
        int h = 0;
        for (int i = 0; i < array.length; i++)
        {
            StringBuilder sb = new StringBuilder ();

            for (int j = 0; j < bestColSize; j++)
            {
                if (h < length)
                {
                    char c = text.charAt (h);
                    h += 1;
                    sb.append (c);
                }
            }

            array[i] = sb.toString ();
        }

        return array;
    }
}
