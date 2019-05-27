package run2right;

import pixel.App;
import pixel.Assets;

public class Main
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 448;

    public static void main (String[] args) throws Exception
    {
        Assets a = Assets.load ("/assets.json");
        IntroScene intro = new IntroScene (a, WIDTH, HEIGHT);
        App app = new App (intro, "Run2Right", WIDTH, HEIGHT);
        app.start ();
    }
}
