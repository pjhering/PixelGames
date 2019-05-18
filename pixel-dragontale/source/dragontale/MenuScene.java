package dragontale;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class MenuScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private final String[] option = {"Start", "Help", "Credits"};
    private int selected = 0;
    private boolean enter = false;
    private Background bg;

    public MenuScene (Assets a, int width, int height)
    {
        this.a = a;
        this.width = width;
        this.height = height;
        this.bg = new Background (a.getImage (1), 0.1);
        this.bg.setVector (0.05, 0);
    }

    @Override
    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (enter)
        {
            switch (selected)
            {
                case 0:
                    mgr.replace (new Level1Scene (a, width, height));
                    break;

                case 1:
                    mgr.replace (new HelpScene (a, width, height));
                    break;

                case 2:
                    mgr.replace (new CreditsScene (a, width, height));
                    break;
            }
        }
        else
        {
            bg.update (elapsedMillis);
        }
    }

    @Override
    public void render (Graphics g)
    {
        bg.draw (g);

        for (int i = 0; i < option.length; i++)
        {
            if (selected != i)
            {
                a.getFont (0).draw (g, option[i], 100, 100 + (i * 40));
            }
            else
            {
                a.getFont (1).draw (g, option[i], 100, 100 + (i * 40));
            }
        }
    }

    @Override
    public void activate ()
    {
        selected = 0;
        enter = false;
    }

    @Override
    public void deactivate ()
    {
    }

    @Override
    public void dispose ()
    {
    }

    @Override
    public void keyReleased (KeyEvent e)
    {
        int key = e.getKeyCode ();

        if (key == KeyEvent.VK_UP)
        {
            selected = selected - 1;

            if (selected < 0)
            {
                selected = option.length - 1;
            }
        }
        else if (key == KeyEvent.VK_DOWN)
        {
            selected = selected + 1;

            if (selected >= option.length)
            {
                selected = 0;
            }
        }
        else if (key == KeyEvent.VK_ENTER)
        {
            enter = true;
        }
    }
}
