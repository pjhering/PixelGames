package spaceinvaders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import pixel.App;

public class Main
{
    public static final int WIDTH  = 640;
    public static final int HEIGHT = 640;

    public static final Stroke STROKE = new BasicStroke (2);

    public static final Color SKY_COLOR    = new Color (0, 0, 0);
    public static final Color GROUND_COLOR = new Color (0, 255, 0);
    public static final Color PLAYER_FILL  = new Color (192, 192, 192);
    public static final Color PLAYER_DRAW  = new Color (255, 255, 255);
    public static final Color BRICK_COLOR  = new Color (192, 0, 0);
    public static final Color MORTAR_COLOR = new Color (64, 64, 64);
    public static final Color BULLET_COLOR = new Color (255, 255, 128);

    public static final int GROUND_HEIGHT = 80;

    public static final int BUILDING_COUNT  = 3;
    public static final int BUILDING_SPACER = 94;
    public static final int BUILDING_WIDTH  = 88;

    public static final int BRICK_WIDTH  = 22;
    public static final int BRICK_HEIGHT = 18;
    public static final int BRICK_COLS   = 4;
    public static final int BRICK_ROWS   = 5;

    public static final double PLAYER_SPEED = 0.25;
    public static final int PLAYER_BOTTOM   = 60;
    public static final int PLAYER_WIDTH    = 30;
    public static final int PLAYER_HEIGHT   = 30;
    public static final long PLAYER_FIRE_DELAY = 150;

    public static final double BULLET_UP_SPEED   = -1.0;
    public static final double BULLET_DOWN_SPEED = 1.0;
    public static final int BULLET_WIDTH    = 4;
    public static final int BULLET_HEIGHT   = 4;

    public static void main (String[] args) throws Exception
    {
        GameScene game = new GameScene ();
        App app = new App (game, "Space Invaders", WIDTH, HEIGHT);
        app.start ();
    }
}
