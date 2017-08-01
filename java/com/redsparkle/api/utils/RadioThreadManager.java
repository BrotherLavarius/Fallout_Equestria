package com.redsparkle.api.utils;

import javax.sound.sampled.FloatControl;
import java.io.IOException;

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
                System.out.println("Sound System was still warming up");
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
                System.out.println("Sound System was still warming up");
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
            try {
                radioPLayer.running = false;
                radioPLayer.line.close();
                radioPLayer.din.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
