package pixel;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import static java.util.Objects.requireNonNull;
import javax.swing.JFrame;
import static javax.swing.JFrame.HIDE_ON_CLOSE;

public class Display
{
    private final String title;
    private final int width;
    private final int height;
    private final Canvas canvas;
    private final JFrame frame;

    public Display (String title, int width, int height)
    {
        this.title = requireNonNull (title);
        this.width = Math.abs (width);
        this.height = Math.abs (height);

        Dimension d = new Dimension (this.width, this.height);
        this.canvas = new Canvas ();
        this.canvas.setSize (d);
        this.canvas.setPreferredSize (d);
        this.canvas.setMinimumSize (d);
        this.canvas.setMaximumSize (d);

        this.frame = new JFrame (title);
        this.frame.add (canvas);
        this.frame.pack ();
        this.frame.setResizable (false);
        this.frame.setLocationRelativeTo (null);
        this.frame.setDefaultCloseOperation (HIDE_ON_CLOSE);
    }

    public void addWindowListener (WindowListener wl)
    {
        this.frame.addWindowListener (wl);
    }

    public void setCanvasListener (CanvasListener cl)
    {
        canvas.addKeyListener (cl);
        canvas.addMouseListener (cl);
        canvas.addMouseMotionListener (cl);
        canvas.addMouseWheelListener (cl);
    }

    public void open ()
    {
        frame.setVisible (true);
        frame.setAlwaysOnTop (true);
        frame.requestFocus ();
        canvas.requestFocusInWindow ();
    }

    public Graphics getDrawGraphics ()
    {
        BufferStrategy strategy = canvas.getBufferStrategy ();

        if (strategy == null)
        {
            canvas.createBufferStrategy (2);
            return null;
        }

        return strategy.getDrawGraphics ();
    }

    public void show ()
    {
        BufferStrategy strategy = canvas.getBufferStrategy ();

        if (strategy != null)
        {
            strategy.show ();
        }
    }

    public void close ()
    {
        for (WindowListener wl : frame.getWindowListeners ())
        {
            frame.removeWindowListener (wl);
        }
        frame.setVisible (false);
        frame.dispose ();
    }
}
