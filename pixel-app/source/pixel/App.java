package pixel;

import static java.awt.DisplayMode.REFRESH_RATE_UNKNOWN;
import static java.util.Objects.requireNonNull;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Stack;

public class App implements SceneManager, Runnable, WindowListener, CanvasListener {
  private final Stack<Scene> stack;
  private final Display display;
  private final String title;
  private final int width;
  private final int height;
  private final Thread thread;
  private final Object lock;
  private long lastTime;
  private boolean running;
  private boolean paused;
  private final long millisPerFrame;
  private AppListener listener;

  public App(Scene initial, String title, int width, int height) {
    this.stack = new Stack<>();
    this.stack.push(initial);
    this.title = requireNonNull(title);
    this.width = Math.abs(width);
    this.height = Math.abs(height);
    this.display = new Display(title, width, height);
    this.thread = new Thread(this, "pixel.App");
    this.lock = new Object();

    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice dev = env.getDefaultScreenDevice();
    DisplayMode mode = dev.getDisplayMode();
    int rate = mode.getRefreshRate();
    millisPerFrame = 1000 / ((rate == REFRESH_RATE_UNKNOWN) ? 30 : rate);
  }

  public void setAppListener(AppListener listener) {
    this.listener = listener;
  }

  public void start() {
    running = true;
    paused = false;
    display.addWindowListener(this);
    display.setCanvasListener(this);
    display.open();
    thread.start();
  }

  @Override
  public void run() {
    if (!stack.empty()) {
      stack.peek().activate();
    }

    lastTime = System.currentTimeMillis();

    LOOP:
    while (running) {
      long currentTime = System.currentTimeMillis();
      long elapsedTime = currentTime - lastTime;
      lastTime = currentTime;

      if (!stack.empty()) {
        Scene game = stack.peek();
        game.update(this, elapsedTime);

        Graphics g = display.getDrawGraphics();

        if (g != null) {
          game.render(g);
          display.show();
        }
      }

      if (elapsedTime < millisPerFrame) {
        try {
          Thread.sleep(millisPerFrame - elapsedTime);
        } catch (Exception ex) {
          break LOOP;
        }
      }

      if (paused) {
        synchronized (lock) {
          try {
            lock.wait();
          } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
          }
        }
      }
    }

    while (!stack.empty()) {
      Scene scene = stack.pop();
      scene.deactivate();
      scene.dispose();
    }

    if (listener != null) {
      listener.appExiting();
    } else {
      System.exit(0);
    }
  }

  @Override
  public void push(Scene scene) {
    if (!stack.empty()) {
      stack.peek().deactivate();
    }

    stack.push(scene);
    stack.peek().activate();
  }

  @Override
  public Scene pop() {
    if (!stack.empty()) {
      Scene old = stack.pop();
      old.deactivate();
      old.dispose();

      if (!stack.empty()) {
        stack.peek().activate();
      }

      return old;
    }

    return null;
  }

  @Override
  public Scene peek() {
    if (!stack.empty()) {
      return stack.peek();
    } else {
      return null;
    }
  }

  @Override
  public boolean replace(Scene scene) {
    if (scene != null && !stack.empty()) {
      Scene old = stack.pop();
      old.deactivate();
      old.dispose();
      scene.activate();
      return stack.push(scene) == scene;
    } else {
      return false;
    }
  }

  @Override
  public void windowOpened(WindowEvent e) {
    lastTime = System.currentTimeMillis();
  }

  @Override
  public void windowIconified(WindowEvent e) {
    synchronized (lock) {
      paused = true;
    }
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
    try {
      synchronized (lock) {
        lastTime = System.currentTimeMillis();
        paused = false;
        lock.notify();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }

  @Override
  public void windowClosing(WindowEvent e) {
    running = false;
  }

  @Override
  public void windowActivated(WindowEvent e) {}

  @Override
  public void windowDeactivated(WindowEvent e) {}

  @Override
  public void windowClosed(WindowEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    if (!stack.empty()) stack.peek().keyPressed(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (!stack.empty()) stack.peek().keyReleased(e);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (!stack.empty()) stack.peek().keyTyped(e);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (!stack.empty()) stack.peek().mousePressed(e);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (!stack.empty()) stack.peek().mouseReleased(e);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (!stack.empty()) stack.peek().mouseClicked(e);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (!stack.empty()) stack.peek().mouseMoved(e);
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (!stack.empty()) stack.peek().mouseDragged(e);
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (!stack.empty()) stack.peek().mouseEntered(e);
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (!stack.empty()) stack.peek().mouseExited(e);
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    if (!stack.empty()) stack.peek().mouseWheelMoved(e);
  }
}
