package spaceinvaders;

import pixel.App;

public class Main
{
    public static final int WIDTH = 640;
    public static final int HEIGHT = 640;

    public static void main (String[] args) throws Exception
    {
        GameScene game = new GameScene ();
        App app = new App (game, "Space Invaders", WIDTH, HEIGHT);
        app.start ();
    }
}
