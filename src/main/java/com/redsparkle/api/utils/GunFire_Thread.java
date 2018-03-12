package com.redsparkle.api.utils;

import com.redsparkle.foe.ClientOnlyProxy;

/**
 * Created by hoijima on 15.09.17.
 */
public class GunFire_Thread {
    public Thread gunFire;
    public Runnable gunFireR;
    public boolean running = false;

    public GunFire_Thread(String type, int bps) {
        running = true;
        gunFire(type, bps);
    }

    public synchronized void gunFire(final String type, int bps) {
        gunFireR = () -> {
            while (running) {
                try {
                    ClientOnlyProxy.FireMessage(type);
                    Thread.sleep(bps);


                } catch (NullPointerException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        gunFire = new Thread(gunFireR);
        gunFire.setName(type);
        gunFire.start();
    }

    public void stopFire() {
        this.running = false;

    }
}
