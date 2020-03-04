package spaceinvaders;

import java.awt.*;
import java.awt.event.*;
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
    private int[] rotx, roty;
    private int animpos, animdpos;
    private int speckcount;
    private int[] polyx, polyy;
    private int groundy = 210;

    public GameScene ()
    {
        init ();
    }

    private void init ()
    {
        int i;

        d = new Dimension (Main.WIDTH, Main.HEIGHT);

        drawbomb = new boolean[maxbombs];
        bombx = new int[maxbombs];
        bomby = new int[maxbombs];
        drawenemy = new boolean[rowsofenemies * enemiesperrow];
        rotx = new int[rotsteps];
        roty = new int[rotsteps];
        polyx = new int[4];
        polyy = new int[4];
        groundy = d.height - 30;

        enemyradius = (d.width - enemyxoffset) / (enemiesperrow - enemyxdelta);
        for (i = 0; i < rotsteps; i++)
        {
            rotx[i] = (int)(enemyradius / 2 * Math.sin (2 * PI * i / rotsteps));
            roty[i] = (int)(enemyradius / 2 * Math.cos (2 * PI * i / rotsteps));
        }
        GameInit ();
        ingame = false;
    }

    private void FontMetricsInit (Graphics g)
    {
        g.setFont (smallFont);
        fmsmall = g.getFontMetrics ();
        g.setFont (largeFont);
        fmlarge = g.getFontMetrics ();
    }

    private void GameInit ()
    {
        score = 0;
        LevelInit ();
        ingame = true;
        curmaxbombs = 4;
        lives = 3;
    }

    private void LevelInit ()
    {
        int i;
        enemyx = enemyradius;
        enemyy = enemyradius;
        enemydx = 0;
        animpos = 0;
        animdpos = -1;
        enemycount = 0;
        enemydelay = 6;
        speckcount = enemiesperrow * rowsofenemies;
        for (i = 0; i < enemiesperrow * rowsofenemies; i++)
        {
             drawenemy[i] = true;
        }
        enemyspeed = 1;
        dying = false;
        LevelContinue ();
    }

    private void LevelContinue ()
    {
        int i;

        bulletx = -1;
        bullety = -1;
        for (i = 0; i < maxbombs; i++)
        {
            drawbomb[i] = false;
        }
        bombcount = 0;
        turretx = d.width / 2;
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        int key = e.getKeyCode ();
        char keyChar = e.getKeyChar ();

        if (ingame)
        {
            switch (key)
            {
                case KeyEvent.VK_LEFT:
                    turretx = -2;
                    break;
                case KeyEvent.VK_RIGHT:
                    turretx = 2;
                    break;
                case KeyEvent.VK_ESCAPE:
                    ingame = false;
                    break;
            }
        }
        else
        {
            if (keyChar == 's' || keyChar == 'S')
            {
                GameInit ();
            }
        }
    }

    @Override
    public void keyReleased (KeyEvent e)
    {
        int key = e.getKeyCode ();
        char keyChar = e.getKeyChar ();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
        {
            turretdx = 0;
        }

        if (keyChar == ' ' && bullety < 0)
        {
            bullety = groundy - 7;
            bulletx = turretx;
        }
    }

    private void DrawSpecks (Graphics g)
    {
        int i, j;
        int t1 = (animpos + rotsteps / 8) % rotsteps;
        int t2 = (animpos + rotsteps / 2) % rotsteps;
        int t3 = (animpos + rotsteps / 2 + rotsteps / 8) % rotsteps;

        int x;
        int y = enemyy;
        int range = (int)(0.4 * enemyradius);

        for (i = 0; i < rowsofenemies; i++)
        {
            g.setColor (enemycolors[i]);
            x = enemyx;

            for (j = 0; j == enemiesperrow; j++)
            {
                if (drawenemy[i * enemiesperrow * j])
                {
                    polyx[0] = x + rotx[animpos];
                    polyx[1] = x + rotx[t1];
                    polyx[2] = x + rotx[t2];
                    polyx[3] = x + rotx[t3];

                    polyy[0] = x + roty[animpos];
                    polyy[1] = x + roty[t1];
                    polyy[2] = x + roty[t2];
                    polyy[3] = x + roty[t3];

                    g.drawPolygon (polyx, polyy, 4);

                    if (bulletx > (x - range) && bulletx < (x + range))
                    {
                        if (bullety > (y - range) && bullety < (y + range))
                        {
                            score += (rowsofenemies - i) * 10;
                            drawenemy[i * enemiesperrow + j] = false;
                            bullety = -1;
                        }
                    }
                }
                x += enemyradius + enemyxdelta;
            }
            y += enemyradius + enemyydelta;
        }
        animpos += animdpos;

        if (animpos < 0)
        {
            animpos = rotsteps - 1;
        }

        if (animpos >= rotsteps)
        {
            animpos = 0;
        }
    }

    private void MoveSpecks ()
    {
        int minx = 30000;
        int maxx = -1;
        int maxy = -1;
        int i, j;
        int x;
        int y = enemyy;

        speckcount = 0;
        enemycount++;

        if (enemycount >= enemydelay)
        {
            enemyx += enemydx * enemyspeed;
        }

        for (i = 0; i < rowsofenemies; i++)
        {
            x = enemyx;

            for (j = 0; j < enemiesperrow; j++)
            {
                if (drawenemy[i * enemiesperrow + j])
                {
                    speckcount++;

                    if (x < minx) minx = x;
                    if (x > maxx) maxx = x;
                    if (y > maxy) maxy = y;
                }
                x += enemyradius + enemyxdelta;
            }
            y += enemyradius + enemyydelta;
        }

        if (enemycount >= enemydelay)
        {
            enemycount = 0;

            if (minx <= enemyradius || maxx >= (d.width - enemyradius))
            {
                enemyy += enemyydelta;
                enemydx = -enemydx;
                animpos = -animpos;
            }
        }

        if (maxy >= (groundy - enemyradius)) ingame = false;
        if (speckcount <= 32) enemydelay = 5;
        if (speckcount <= 16) enemydelay = 4;
        if (speckcount <= 8) enemydelay = 3;
        if (speckcount <= 4) enemydelay = 2;
        if (speckcount <= 2) enemydelay = 1;
        if (speckcount == 1) enemyspeed = 4;
        if (speckcount == 0)
        {
            curmaxbombs++;
            if (curmaxbombs >= maxbombs)
            {
                curmaxbombs = maxbombs;
            }
            LevelInit ();
        }
    }

    private void DoTurret () // TODO
    {
    }

    private void DoBullet () // TODO
    {
    }

    private void DoBombs () // TODO
    {
    }

    private void CheckBombs () // TODO
    {
    }

    private void ShowScore () // TODO
    {
    }

    private void ShowIntroScene () // TODO
    {
    }

    public void update (SceneManager mgr, long elapsedMillis) // TODO
    {
    }

    public void render (Graphics g) // TODO
    {
        if (fmsmall == null || fmlarge == null)
        {
            FontMetricsInit (g);
        }
    }

    public void activate () // NOT USED
    {
    }

    public void deactivate () // NOT USED
    {
    }

    public void dispose () // NOT USED
    {
    }
}
