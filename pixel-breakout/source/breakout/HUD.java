package breakout;

import java.awt.Graphics;
import pixel.Assets;
import pixel.BitmapFont;

public class HUD
{
    private final BitmapFont font;
    public int bricksRemaining;
    public int livesRemaining;

    private String livesFormat;
    private int livesX = 20;
    private int livesY = 600;

    private String bricksFormat;
    private int bricksX = 520;
    private int bricksY = 600;

    public HUD (Assets a, int bricks, int lives)
    {
        this.font = a.getFont (2);
        this.livesFormat = a.getString (2);
        this.bricksFormat = a.getString (3);
        this.bricksRemaining = bricks;
        this.livesRemaining = lives;
    }

    public void render (Graphics g)
    {
        font.draw (g, String.format (livesFormat, livesRemaining), livesX, livesY);
        font.draw (g, String.format (bricksFormat, bricksRemaining), bricksX, bricksY);
    }
}
