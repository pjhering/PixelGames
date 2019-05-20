package pixel.maps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

pubilc class Background
{
    private BufferedImage image;

    public Background (BufferedImage image)
    {
        this.image = image;
    }

    public void draw (Graphics g)
    {
        g.drawImage (image, 0, 0, null);
    }
}
