package com.redsparkle.api.utils;

import com.sun.media.jfxmedia.logging.Logger;

import javax.sound.sampled.FloatControl;

public class RadioThreadManager {
    public static RadioPLayer radioPLayer;
    public static Thread radio;
    public static FloatControl gain;
    public static boolean status;

    public static void SpawnRadio(String address) {
        radioPLayer = new RadioPLayer(address);
        status = true;
        radio = getThreadByName("RadioPlayerThread");
    }

    public static boolean isTreadAlive() {
        return getThreadByName("RadioPlayerThread") != null;
    }

    public static void increaseSound() {
        if (isTreadAlive()) {
            try {
                gain = (FloatControl) radioPLayer.line.getControl(FloatControl.Type.MASTER_GAIN);
                if (gain.getValue() < (gain.getMaximum() - 2.0F)) {
                    gain.setValue(gain.getValue() + 1.0F);
                }

            } catch (NullPointerException e) {
                Logger.logMsg(Logger.ERROR, "Sound System was still warming up");
            }
        }
    }

    public static void decreaseSound() {
        if (isTreadAlive()) {
            try {
                gain = (FloatControl) radioPLayer.line.getControl(FloatControl.Type.MASTER_GAIN);
                if (gain.getValue() > (gain.getMinimum() + 2.0F)) {
                    gain.setValue(gain.getValue() - 1.0F);
                }
            } catch (NullPointerException e) {
                Logger.logMsg(Logger.ERROR, "Sound System was still warming up");
            }
        }
    }


    public static Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) return t;
        }
        return null;
    }

    public static void StopPlayer() {
        if (isTreadAlive()) {
            radioPLayer.running = false;
            radioPLayer.player.stop();
        }
    }
}
