package pixel;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public abstract class Scene
    implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
    public abstract void update (SceneManager mgr, long elapsedMillis);

    public abstract void render (Graphics g);

    public abstract void activate ();

    public abstract void deactivate ();

    public abstract void dispose ();

    @Override
    public void keyPressed (KeyEvent e){}

    @Override
    public void keyReleased (KeyEvent e){}

    @Override
    public void keyTyped (KeyEvent e){}

    @Override
    public void mousePressed (MouseEvent e){}

    @Override
    public void mouseReleased (MouseEvent e){}

    @Override
    public void mouseClicked (MouseEvent e){}

    @Override
    public void mouseEntered (MouseEvent e){}

    @Override
    public void mouseExited (MouseEvent e){}

    @Override
    public void mouseMoved (MouseEvent e){}

    @Override
    public void mouseDragged (MouseEvent e){}

    @Override
    public void mouseWheelMoved (MouseWheelEvent e){}
}
