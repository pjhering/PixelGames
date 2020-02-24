package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;
import pixel.Assets;
import pixel.Scene;
import pixel.SceneManager;

public class GameScene extends Scene
{
    private final Assets a;
    private final int width;
    private final int height;
    private boolean paused;
    private boolean gameOver;
    private boolean countdown;
    private final PausedScene pausedScene;
    private final CountDownScene countDownScene;
    private final HUD hud;
    private final Paddle paddle;
    private final Ball ball;
    private final Brick[] bricks;

    public GameScene (Assets a, int width, int height)
    {
        this.a = Objects.requireNonNull (a);
        this.width = Math.abs (width);
        this.height = Math.abs (height);

        pausedScene = new PausedScene (a, width, height);
        countDownScene = new CountDownScene (a, width, height);

        hud = new HUD (a, 50, 3);

        paddle = new Paddle (0, 620, 160, 640, a.getSprite (0));

        ball = new Ball (310, 160, 330, 180, a.getSprite (1));
        ball.reset ();

        bricks = new Brick[56];
        int i = 0;
        for (int col = 0; col < 8; col++)
        {
            int x1 = col * 80;
            int x2 = x1 + 80;

            for (int row = 0; row < 7; row++)
            {
                int y1 = row * 20;
                int y2 = y1 + 20;

                int s = (i % 6) + 2;
                bricks[i] = new Brick (a.getSprite (s), x1, y1, x2, y2);
                i += 1;
            }
        }
    }

    public void update (SceneManager mgr, long elapsedMillis)
    {
        if (paused)
        {
            mgr.push (pausedScene);
        }
        else if (gameOver)
        {
            mgr.replace (new GameOverScene (a, width, height));
        }
        else if (countdown)
        {
            mgr.push (countDownScene);
        }
        else
        {
            updateGame (elapsedMillis);
        }
    }

    private void updateGame (long elapsedMillis)
    {
        ball.update (elapsedMillis);
        // TODO: detect ball-paddle collision
        // TODO: detect ball-brick collision
        // TODO: detect top ball-wall collision
        // TODO: detect left ball-wall collision
        // TODO: detect right ball-wall collision
        // TODO: detect ball-floor collision
        // TODO: update game state
    }

    public void render (Graphics g)
    {
        g.setColor (a.getColor (0));
        g.fillRect (0, 0, width, height);
        // render bricks
        for (Brick b : bricks)
        {
            if (!b.hit)
            {
                b.render (g);
            }
        }
        // render ball
        ball.render (g);
        // render paddle
        paddle.render (g);
        hud.render (g);
    }

    public void activate ()
    {
        paused = false;
        gameOver = false;
        countdown = false;
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
        paddle.update (e.getX ());
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        switch (e.getKeyCode ())
        {
            case KeyEvent.VK_Q:
                gameOver = true;
                break;
            case KeyEvent.VK_P:
                paused = true;
                break;
            case KeyEvent.VK_C:
                countdown = true;
                break;
        }
    }
}
