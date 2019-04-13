package pixel;

import java.net.URL;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Music
{
    private final Sequence music;
    private final Sequencer device;

    public Music (URL url) throws Exception
    {
        music = MidiSystem.getSequence (url);
        device = MidiSystem.getSequencer ();
    }

    public void play ()
    {
        try
        {
            if (!device.isOpen ())
            {
                device.open ();
            }

            if (device.isRunning ())
            {
                device.stop ();
            }

            if (device.getSequence () != this.music)
            {
                device.setSequence (music);
                device.setLoopCount (-1);
            }

            device.start ();
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }

    public void stop ()
    {
        if (device.isOpen ())
        {
            device.stop ();
        }
    }

    public void quit ()
    {
        if (device.isOpen ())
        {
            if (device.isRunning ())
            {
                device.stop ();
            }

            device.setTickPosition (0);
            device.close ();
        }
    }
}
