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
    public static final Color BULLET_COLOR1 = new Color (255, 255, 255);
    public static final Color BULLET_COLOR2 = new Color (255, 0, 0);
    public static final Color[][] INVADER_COLORS = new Color[][] {{
            new Color (0, 0, 100), new Color (0, 0, 120), new Color (0, 0, 140), new Color (0, 0, 160),
            new Color (0, 0, 180), new Color (0, 0, 200), new Color (0, 0, 220), new Color (0, 0, 240)
        }, {new Color (0, 100, 0), new Color (0, 120, 0), new Color (0, 140, 0), new Color (0, 160, 0),
            new Color (0, 180, 0), new Color (0, 200, 0), new Color (0, 220, 0), new Color (0, 240, 0)
        }, {new Color (100, 0, 0), new Color (120, 0, 0), new Color (140, 0, 0), new Color (160, 0, 0),
            new Color (180, 0, 0), new Color (200, 0, 0), new Color (220, 0, 0), new Color (240, 0, 0)
        }, {new Color (100, 0, 100), new Color (120, 0, 120), new Color (140, 0, 140), new Color (160, 0, 160),
            new Color (180, 0, 180), new Color (200, 0, 200), new Color (220, 0, 220), new Color (240, 0, 240)
        }, {new Color (0, 100, 100), new Color (0, 120, 120), new Color (0, 140, 140), new Color (0, 160, 160),
            new Color (0, 180, 180), new Color (0, 200, 200), new Color (0, 220, 220), new Color (0, 240, 240)}};

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

    public static final int INVADER_ROWS = 5;
    public static final int INVADER_COLS = 10;
    public static final double INVADER_WIDTH  = 36;
    public static final double INVADER_HEIGHT = 36;
    public static final double INVADER_SPACER = 16;
    public static final double INVADER_BOTTOM = 240;

    public static void main (String[] args) throws Exception
    {
        GameScene game = new GameScene ();
        App app = new App (game, "Space Invaders", WIDTH, HEIGHT);
        app.start ();
    }
}
