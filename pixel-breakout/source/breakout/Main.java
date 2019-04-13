package breakout;

import pixel.App;
import pixel.Assets;

public class Main
{
    public static final int WIDTH = 640;
    public static final int HEIGHT = 640;

    public static void main (String[] args) throws Exception
    {
        Assets a = Assets.load ("/text/assets.json");
        IntroScene intro = new IntroScene (a, WIDTH, HEIGHT);
        App app = new App (intro, "Breakout", WIDTH, HEIGHT);
        app.start ();
    }
}
