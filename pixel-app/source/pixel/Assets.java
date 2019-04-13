package pixel;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.midi.Sequence;

public class Assets
{
    public Assets () {}

    public static Assets load (String jsonFile) throws Exception
    {
        URL url = Assets.class.getResource (jsonFile);

        try (InputStream stream = url.openStream ())
        {
            InputStreamReader reader = new InputStreamReader (stream);
            Gson gson = new Gson ();
            Assets assets = gson.fromJson (reader, Assets.class);
            loadImages (assets);
            loadSounds (assets);
            loadMusics (assets);
            loadSprites (assets);
            loadFonts (assets);
            loadColors (assets);
            return assets;
        }
    }

    private static void loadImages (Assets a) throws Exception
    {
        if (a.imageFiles == null) return;

        int length = a.imageFiles.length;
        a.images = new BufferedImage[length];

        for (int i = 0; i < length; i++)
        {
            URL url = Assets.class.getResource (a.imageFiles[i]);
            BufferedImage orig = ImageIO.read (url);
            int width = orig.getWidth ();
            int height = orig.getHeight ();
            a.images[i] = new BufferedImage (width, height, TYPE_INT_ARGB);
            Graphics g = a.images[i].getGraphics ();
            g.drawImage (orig, 0, 0, width, height, null);
        }
    }

    private static void loadSounds (Assets a) throws Exception
    {
        if (a.clipFiles == null) return;

        int length = a.clipFiles.length;
        a.sounds = new Sound[length];

        for (int i = 0; i < length; i++)
        {
            URL url = Assets.class.getResource (a.clipFiles[i]);
            a.sounds[i] = new Sound (url);
        }
    }

    private static void loadMusics (Assets a) throws Exception
    {
        if (a.midiFiles == null) return;

        int length = a.midiFiles.length;
        a.musics = new Music[length];

        for (int i = 0; i < length; i++)
        {
            URL url = Assets.class.getResource (a.midiFiles[i]);
            a.musics[i] = new Music (url);
        }
    }

    private static void loadSprites (Assets a) throws Exception
    {
        if (a.spriteDefs == null) return;

        int length = a.spriteDefs.length;
        a.sprites = new Sprite[length];

        for (int i = 0; i < length; i++)
        {
            a.sprites[i] = createSprite (a, a.spriteDefs[i]);
        }
    }

    private static Sprite createSprite (Assets a, int[] i)
    {
        if (i.length == 5)
        {
            BufferedImage img = a.getImage (i[0]);
            return new ImageSprite (img, i[1], i[2], i[3], i[4]);
        }
        else if (i.length == 8)
        {
            BufferedImage img = a.getImage (i[0]);
            return new AnimatedSprite (img, i[1], i[2], i[3], i[4], i[5], i[6], i[7]);
        }

        return null;
    }

    private static void loadFonts (Assets a) throws Exception
    {
        if (a.fontFiles == null) return;

        int length = a.fontFiles.length;
        a.fonts = new BitmapFont[length];

        for (int i = 0; i < length; i++)
        {
            URL url = Assets.class.getResource (a.fontFiles[i]);
            a.fonts[i] = BitmapFont.load (url);
        }
    }

    private static void loadColors (Assets a) throws Exception
    {
        if (a.colorDefs == null) return;

        int length = a.colorDefs.length;
        a.colors = new Color[length];

        for (int i = 0; i < length; i++)
        {
            a.colors[i] = Color.decode (a.colorDefs[i]);
        }
    }

    private String[] imageFiles;
    public String[] getImageFiles () { return imageFiles; }
    public void setImageFiles (String[] value) { imageFiles = value; }

    private BufferedImage[] images;
    public BufferedImage getImage (int i) { return images[i]; }
    public SpriteSheet getSpriteSheet (int i) { return new SpriteSheet (images[i]); }

    private String[] clipFiles;
    public String[] getClipFiles () { return clipFiles; }
    public void setClipFiles (String[] value) { clipFiles = value; }

    private Sound[] sounds;
    public Sound getSound (int i) { return sounds[i]; }

    private String[] midiFiles;
    public String[] getMidiFiles () { return midiFiles; }
    public void setMidiFiles (String[] value) { midiFiles = value; }

    private Music[] musics;
    public Music getMusic (int i) { return musics[i]; }

    private Sequence[] sequences;
    public Sequence getSequence (int i) { return sequences[i]; }

    private String[] fontFiles;
    public String[] getFontFiles () { return fontFiles; }
    public void setFontFiles (String[] value) { fontFiles = value; }

    private BitmapFont[] fonts;
    public BitmapFont getFont (int i) { return fonts[i]; }

    private int[][] spriteDefs;
    public int[][] getSpriteDefs () { return spriteDefs; }
    public void setSpriteDefs (int[][] value) { spriteDefs = value; }

    private Sprite[] sprites;
    public Sprite getSprite (int i) { return sprites[i]; }
    public Sprite[] getSprites () { return sprites; }

    private String[] colorDefs;
    public String[] getColorDefs () { return colorDefs; }
    public void setColorDefs (String[] value) { colorDefs = value; }

    private Color[] colors;
    public Color getColor (int i) { return colors[i]; }

    private String[] strings;
    public String[] getStrings () { return strings; }
    public void setStrings (String[] value) { strings = value; }

    public String getString (int i) { return strings[i]; }
}
