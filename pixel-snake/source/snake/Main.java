package snake;

import pixel.App;
import pixel.Assets;

public class Main
{
    public static final int WIDTH = 640;
    public static final int HEIGHT = 640;

    public static void main (String[] args) throws Exception
    {
        Assets a = Assets.load ("/text/assets.json");
        BeforeScene intro = new BeforeScene (a, WIDTH, HEIGHT);
        App app = new App (intro, "SNAKE!", WIDTH, HEIGHT);
        app.start ();
    }
}
