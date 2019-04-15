package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class PlayScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private final int rows;
    private final int columns;
    private boolean gameOver;
    private Head head;
    private List<Body> bodyList;
    private Heart heart;
    private long elapsed;
    private long moveTime = 375L;
    private boolean addBody;

    public PlayScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);
        this.rows = width / 32;
        this.columns = height / 32;

        this.bodyList = new ArrayList<> ();
        this.head = new Head (0, 0, 32, 32, a.getSprite (0));
        this.bodyList.add (new Body (0, 32, 32, 32, a.getSprite (1)));
        this.bodyList.add (new Body (0, 64, 32, 32, a.getSprite (2)));
        this.bodyList.add (new Body (0, 96, 32, 32, a.getSprite (1)));
        this.bodyList.add (new Body (0,128, 32, 32, a.getSprite (2)));
        heart = new Heart (0, 0, 32, 32, a.getSprite (3));
        resetHeart ();
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (gameOver)
        {
            mgr.replace (new AfterScene (a, width, height));
        }
        else
        {
            updateGame (mgr, elapsedMillis);
        }
    }

    private void updateGame (SceneManager mgr, long elapsedMillis)
    {
        elapsed += elapsedMillis;

        while (elapsed > moveTime)
        {
            elapsed -= moveTime;

            Body newBody = null;

            if (addBody)
            {
                int s = (bodyList.size () % 2 == 0) ? 1 : 2;
                newBody = new Body (-99, -99, 32, 32, a.getSprite (s));
                newBody.moveTo (bodyList.get (0));
            }

            for (int i = bodyList.size () - 1; i > 0; i--)
            {
                Body b1 = bodyList.get (i);
                Body b2 = bodyList.get (i - 1);
                b1.moveTo (b2);
            }

            if (addBody)
            {
                bodyList.add (newBody);
                addBody = false;
            }

            bodyList.get (0).moveTo (head);
            head.move ();

            if (head.hits (heart))
            {
                a.getSound (0).play ();
                resetHeart ();
                addBody = true;
            }

            for (Body b : bodyList)
            {
                if (head.hits (b))
                {
                    a.getSound (1).play ();
                    mgr.replace (new AfterScene (a, width, height));
                }
            }
        }
    }

    private void resetHeart ()
    {
        LOOP:
        while (true)
        {
            if (head.hits (heart))
            {
                heart.randomize (rows, columns);
                continue LOOP;
            }
            else
            {
                for (Body b : bodyList)
                {
                    if (b.hits (heart))
                    {
                        heart.randomize (rows, columns);
                        continue LOOP;
                    }
                }
            }

            break LOOP;
        }
    }

    public void render (Graphics g)
    {
        g.setColor (a.getColor (0));
        g.fillRect (0, 0, width, height);

        head.draw (g);
        bodyList.forEach (b -> b.draw (g));
        heart.draw (g);
        drawGrid (g);
    }

    private void drawGrid (Graphics g)
    {
        g.setColor (Color.CYAN);

        for (int x = 0; x < width; x += 32)
        {
            g.drawLine (x, 0, x, height);
        }

        for (int y = 0; y < height; y += 32)
        {
            g.drawLine (0, y, width, y);
        }
    }

    public void activate ()
    {
        gameOver = false;
        elapsed = 0L;
        addBody = false;
    }

    public void deactivate ()
    {
    }

    public void dispose ()
    {
    }

    @Override
    public void mouseMoved (MouseEvent e)
    {
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        switch (e.getKeyCode ())
        {
            case KeyEvent.VK_UP:
                if (head.direction != Head.DOWN)
                {
                    head.direction = Head.UP;
                }
                break;

            case KeyEvent.VK_DOWN:
                if (head.direction != Head.UP)
                {
                    head.direction = Head.DOWN;
                }
                break;

            case KeyEvent.VK_LEFT:
                if (head.direction != Head.RIGHT)
                {
                    head.direction = Head.LEFT;
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (head.direction != Head.LEFT)
                {
                    head.direction = Head.RIGHT;
                }
                break;
        }
    }
}
