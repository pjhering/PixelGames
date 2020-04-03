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
        try (InputStream stream = url.openStream ())
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream (stream);
            clip = AudioSystem.getClip ();
            clip.open (ais);
        }
    }

    public void play ()
    {
        clip.stop ();
        clip.flush ();
        clip.setFramePosition (0);
        clip.start ();
    }

    public void dispose ()
    {
        clip.stop ();
        clip.flush ();
        clip.close ();
    }
}
