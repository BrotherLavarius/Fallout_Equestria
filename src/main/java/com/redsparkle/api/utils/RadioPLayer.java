package com.redsparkle.api.utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by hoijima on 10.06.17.
 */
public class RadioPLayer {
    public Thread player;
    public Runnable playerR;
    public String playerThreadState = "";
    public AudioInputStream din;
    public AudioInputStream in;
    public SourceDataLine line;
    public SourceDataLine res;
    public boolean running;
    public FloatControl gain;

    public RadioPLayer(String arg) {
        running = true;
        playSound(arg);
    }

    public synchronized void playSound(final String Url) {
        playerR = new Runnable() {
            public void run() {
                while (running) {
                    try {
                        playerThreadState = "running";
                        URL file = new URL(Url);
                        // Get AudioInputStream from given file.
                        in = AudioSystem.getAudioInputStream(file);
                        din = null;
                        if (in != null) {
                            AudioFormat baseFormat = in.getFormat();
                            AudioFormat decodedFormat = new AudioFormat(
                                    AudioFormat.Encoding.PCM_SIGNED,
                                    baseFormat.getSampleRate(),
                                    16,
                                    baseFormat.getChannels(),
                                    baseFormat.getChannels() * 2,
                                    baseFormat.getSampleRate(),
                                    false);
                            // Get AudioInputStream that will be decoded by underlying VorbisSPI
                            din = AudioSystem.getAudioInputStream(decodedFormat, in);
                            // Play now !
                            rawplay(decodedFormat, din);
                            in.close();
                        }
                    } catch (Exception e) {
                        running = false;
                        e.printStackTrace();
                    }
                }
            }

            private void rawplay(AudioFormat targetFormat,
                                 AudioInputStream din) throws IOException, LineUnavailableException {
                byte[] data = new byte[4096];
                line = getLine(targetFormat);
                if (line != null) {
                    // Start
                    line.start();
                    int nBytesRead = 0, nBytesWritten = 0;
                    while (nBytesRead != -1) {
                        nBytesRead = din.read(data, 0, data.length);
                        if (nBytesRead != -1) nBytesWritten = line.write(data, 0, nBytesRead);
                    }
                    // Stop
                    line.drain();
                    line.stop();
                    line.close();
                    din.close();
                    playerThreadState = "";
                }
            }

            private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
                res = null;
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                res = (SourceDataLine) AudioSystem.getLine(info);
                res.open(audioFormat);
                return res;
            }

        };
        player = new Thread(playerR);
        player.setName("RadioPlayerThread");
        player.start();
    }

    public synchronized void LowerSound() {
        if (player.isAlive()) {
            gain = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            if (gain.getValue() < (gain.getMaximum() - 2.0F)) {
                gain.setValue(gain.getValue() + 1.0F);
            }
        }
    }

    public synchronized void RaiseSound() {
        if (player.isAlive()) {
            gain = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            if (gain.getValue() > (gain.getMinimum() + 2.0F)) {
                gain.setValue(gain.getValue() - 1.0F);
            }
        }
    }

}
