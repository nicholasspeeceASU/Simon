
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Description: This class allows for playing sounds on a new Thread
 *
 * @author Jonathan George
 *
 * @version 1.0
 */
public class Sound implements Runnable {

    String inputFile;

    @Override
    public void run() {
        playSound(inputFile);
    }

    public Sound(String pWavFile) {
        inputFile = pWavFile;
    }

    private void playSound(String wavFile) {
        try {
            Clip soundSample = AudioSystem.getClip();
            soundSample.open(AudioSystem.getAudioInputStream(this.getClass()
                    .getResource(wavFile)));
            soundSample.start();
            Thread.sleep(soundSample.getMicrosecondLength() / 1000);

        }
        catch (UnsupportedAudioFileException ex) {
            System.out.println("There has been an error in playSound method "
                    + "- Unsupported File");
        }
        catch (IOException ex) {
            System.out.println("There has been an error in playSound method"
                    + " - IO Exception");
        }
        catch (LineUnavailableException ex) {
            System.out.println("There has been an error in playSound method"
                    + " - Line Unavailable");
        }
        catch (InterruptedException ex) {
            System.out.println("There has been an error in playSound method"
                    + " - Iterrupted Exception");
        }
    }
}
