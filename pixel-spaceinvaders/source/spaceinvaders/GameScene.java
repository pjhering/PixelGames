package spaceinvaders;

import java.awt.*;
import pixel.*;

public class GameScene extends Scene
{
    private static final double PI = 3.1415926535897932;
    
    private Dimension d;
    private Font largeFont = new Font ("Helvetica", Font.BOLD, 24);
    private Font smallFont = new Font ("Helvetica", Font.BOLD, 14);
    private FontMetrics fmsmall, fmlarge;
    private final int rowsofenemies = 6;
    private final int enemiesperrow = 10;
    private final int maxbombs = 10;
    private final int enemyxdelta = 8;
    private final int enemyydelta = 10;
    private final int enemyxoffset = 80;
    private final int groundheight = 5;
    private final int rotsteps = 32;
    private final int turretmarge = 24;
    private final int bombspeed = 3;
    private final Color turretcolor = new Color (0, 200, 200);
    private final Color enemycolor1 = new Color (255, 255, 0);
    private final Color enemycolor2 = new Color (255, 200, 20);
    private final Color enemycolor3 = new Color (255, 150, 40);
    private final Color enemycolor4 = new Color (255, 100, 60);
    private final Color enemycolor5 = new Color (255, 50, 80);
    private final Color enemycolor6 = new Color (255, 0, 100);
    private final Color bulletcolor = new Color (255, 255, 255);
    private final Color bombcolor   = new Color (255, 255, 255);
    private final Color groundcolor = new Color (0, 255, 0);
    private final Color backgnd     = new Color (0, 0, 32);
    private final Color textcolor1  = new Color (255, 160, 64);
    private final Color textcolor2  = new Color (96, 128, 255);
    private final Color textcolor3  = new Color (255, 96, 128);
    private final Color[] enemycolors = {enemycolor1, enemycolor2, enemycolor3,
                                         enemycolor4, enemycolor5, enemycolor6};
    private int[] bombx, bomby;
    private int bombcount, curmaxbombs;
    private boolean[] drawbomb;
    private boolean ingame = false;
    private int bulletx, bullety;
    private int enemyx, enemyy, enemydx;
    private int enemyradius, enemyspeed;
    private int enemycount, enemydelay;
    private int turretx, turretdx;
    private boolean[] drawenemy;
    private int score;
    private int lives = 4;
    private boolean dying = false;
    private int rotx, roty;
    private int[] animpos, animdpos;
    private int speckcount;
    private int[] polyx, polyy;
    private int groundy = 210;

    public GameScene ()
    {
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
    }

    public void render (Graphics g)
    {
        g.setColor (Color.GREEN);
        g.fillRect (0, 0, Main.WIDTH, Main.HEIGHT);
    }

    public void activate ()
    {
    }

    public void deactivate ()
    {
    }

    public void dispose ()
    {
    }
}
