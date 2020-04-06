package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import pixel.Scene;
import pixel.SceneManager;
import static pixel.Utility.debug;
import static spaceinvaders.Main.*;

public class GameScene extends Scene
{
    private Player player;
    private Ground ground;
    private List<Brick> bricks;
    private List<Bullet> playerBullets;
    private List<Bullet> deadBullets;
    private List<Invader> invaders;

    public GameScene ()
    {
        player = createPlayer ();
        ground= new Ground (0, HEIGHT - GROUND_HEIGHT, WIDTH, HEIGHT);
        bricks = createBuildings ();
        playerBullets = new ArrayList<> ();
        deadBullets = new ArrayList<> ();
        invaders = createInvaders ();
    }

    private List<Invader> createInvaders ()
    {
        List<Invader> list = new ArrayList<> ();
        for (int a = 0; a < INVADER_ROWS; a++)
        {
            double y1 = INVADER_SPACER + (INVADER_HEIGHT * a) + (INVADER_SPACER * a);
            double y2 = y1 + INVADER_HEIGHT;

            for (int b = 0; b < INVADER_COLS; b++)
            {
                double x1 = INVADER_SPACER + (INVADER_WIDTH * b) + (INVADER_SPACER * b);
                double x2 = x1 + INVADER_WIDTH;

                list.add (new Invader (x1, y1, x2, y2, INVADER_COLORS[a]));
            }
        }
        return list;
    }

    private List<Brick> createBuildings ()
    {
        List<Brick> list = new ArrayList<> ();

        double x0 = 0.0;

        for (int a = 0; a < BUILDING_COUNT; a++)
        {
            x0 += BUILDING_SPACER;

            for (int b = 0; b < BRICK_COLS; b++)
            {
                double x1 = x0 + (b * BRICK_WIDTH);
                double x2 = x1 + BRICK_WIDTH;

                for (int c = 0; c < BRICK_ROWS; c++)
                {
                    double y1 = HEIGHT - (2 * GROUND_HEIGHT) + (c * BRICK_HEIGHT);
                    double y2 = y1 + BRICK_HEIGHT;

                    list.add (new Brick (x1, y1, x2, y2));
                }
            }

            x0 += BUILDING_WIDTH;
        }

        return list;
    }

    private Player createPlayer ()
    {
        double x1 = (WIDTH - PLAYER_WIDTH) / 2.0;
        double x2 = x1 + PLAYER_WIDTH;
        double y1 = HEIGHT - PLAYER_BOTTOM;
        double y2 = y1 + PLAYER_HEIGHT;

        return new Player (x1, y1, x2, y2);
    }

    @Override
    public void activate ()
    {
    }

    @Override
    public void update (SceneManager m, long elapsed)
    {
        // update player
        player.update (elapsed);

        // update player bullets
        for (Bullet b : playerBullets)
        {
            b.update (elapsed);

            if (b.getY1 () < 0.0)
            {
                deadBullets.add (b);
            }
        }
        playerBullets.removeAll (deadBullets);
        deadBullets.clear ();
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        switch (e.getKeyCode ())
        {
            case KeyEvent.VK_LEFT:
                player.goLeft ();
                break;

            case KeyEvent.VK_RIGHT:
                player.goRight ();
                break;

            case KeyEvent.VK_UP:
                if (player.fire ())
                {
                    Bullet b = Bullet.createFor (player);
                    synchronized (playerBullets)
                    {
                        playerBullets.add (b);
                    }
                }
                break;
        }
    }

    @Override
    public void keyReleased (KeyEvent e)
    {
        switch (e.getKeyCode ())
        {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                player.stop ();
                break;
        }
    }

    @Override
    public void render (Graphics g)
    {
        ((Graphics2D)g).setStroke (STROKE);

        g.setColor (SKY_COLOR);
        g.fillRect (0, 0, WIDTH, HEIGHT);

        ground.draw (g);

        for (Brick b : bricks)
        {
            if (b.isVisible ())
            {
                b.draw (g);
            }
        }

        player.draw (g);

        for (Invader i : invaders)
        {
            if (i.isVisible ())
            {
                i.draw (g);
            }
        }

        for (Bullet b : playerBullets)
        {
            if (b.isVisible ())
            {
                b.draw (g);
            }
        }
    }

    @Override
    public void deactivate ()
    {
    }

    @Override
    public void dispose ()
    {
    }
}
