package pixel;

public class Glyph
{
    public final int sx1;
    public final int sy1;
    public final int sx2;
    public final int sy2;
    public final int width;
    public final int ascent;
    public final int descent;

    public Glyph (int x1, int y1, int x2, int y2, int descent)
    {
        this.sx1 = x1;
        this.sy1 = y1;
        this.sx2 = x2;
        this.sy2 = y2;
        this.width = x2 - x1;
        this.ascent = (y2 - y1) - descent;
        this.descent = descent;
    }
}
