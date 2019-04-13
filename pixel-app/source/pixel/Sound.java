package pixel;

import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

public class Sound
{
    private URL url;
    private Clip clip;

    public Sound (URL url) throws Exception
    {
        this.url = url;
    }

    public void play ()
    {
        if (clip == null || !clip.isActive ())
        {
            try (AudioInputStream stream = AudioSystem.getAudioInputStream (url))
            {
                Line.Info info = new Line.Info (Clip.class);
                Clip clip = (Clip) AudioSystem.getLine (info);
                clip.open (stream);
                clip.loop (0);
            }
            catch (Exception ex)
            {
                ex.printStackTrace ();
            }
        }
    }
}
