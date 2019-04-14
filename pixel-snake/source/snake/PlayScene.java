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
    private boolean gameOver;
    private Head head;
    private List<Body> bodyList;
    private Heart heart;
    private long elapsed;
    private long moveTime = 375L;

    public PlayScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);
        this.bodyList = new ArrayList<> ();
        this.head = new Head (0, 0, 32, 32, a.getSprite (0));
        this.bodyList.add (new Body (0, 32, 32, 32, a.getSprite (1)));
        this.bodyList.add (new Body (0, 64, 32, 32, a.getSprite (1)));
        this.bodyList.add (new Body (0, 96, 32, 32, a.getSprite (1)));
        this.bodyList.add (new Body (0,128, 32, 32, a.getSprite (1)));
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (gameOver)
        {
            mgr.replace (new AfterScene (a, width, height));
        }
        else
        {
            updateGame (elapsedMillis);
        }
    }

    private void updateGame (long elapsedMillis)
    {
        elapsed += elapsedMillis;

        while (elapsed > moveTime)
        {
            elapsed -= moveTime;
            for (int i = bodyList.size () - 1; i > 0; i--)
            {
                Body b1 = bodyList.get (i);
                Body b2 = bodyList.get (i - 1);
                b1.moveTo (b2);
            }
            bodyList.get (0).moveTo (head);
            head.move ();
        }
    }

    public void render (Graphics g)
    {
        g.setColor (a.getColor (0));
        g.fillRect (0, 0, width, height);

        head.draw (g);
        bodyList.forEach (b -> b.draw (g));
//        drawGrid (g);
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
            case KeyEvent.VK_Q:
                gameOver = true;
                break;

            case KeyEvent.VK_UP:
                head.direction = Head.UP;
                break;

            case KeyEvent.VK_DOWN:
                head.direction = Head.DOWN;
                break;

            case KeyEvent.VK_LEFT:
                head.direction = Head.LEFT;
                break;

            case KeyEvent.VK_RIGHT:
                head.direction = Head.RIGHT;
                break;
        }
    }
}
