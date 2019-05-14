package dragontale;

import pixel.App;
import pixel.Assets;

public class Main
{
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public static void main (String[] args) throws Exception
    {
        Assets a = Assets.load ("/assets.json");
        MenuScene intro = new MenuScene (a, WIDTH, HEIGHT);
        App app = new App (intro, "Dragon Tale", WIDTH, HEIGHT);
        app.start ();
    }
}
