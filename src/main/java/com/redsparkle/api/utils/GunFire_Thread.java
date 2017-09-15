package com.redsparkle.api.utils;

import com.redsparkle.foe.ClientOnlyProxy;

/**
 * Created by hoijima on 15.09.17.
 */
public class GunFire_Thread {
    public Thread gunFire;
    public Runnable gunFireR;
    public boolean running = false;

    public GunFire_Thread(String arg) {
        running = true;
        gunFire(arg);
    }

    public synchronized void gunFire(final String type) {
        gunFireR = new Runnable() {
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(200);
                        ClientOnlyProxy.FireMessage(type);

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
