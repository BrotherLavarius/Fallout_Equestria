package com.redsparkle.api.utils;

/**
 * Created by hoijima on 15.09.17.
 */
public class GunFire_ThreadManager {

    public static GunFire_Thread gunFire_thread;
    public static Thread gunfire;
    public static boolean status;


    public static void SpawnGunFire(String gunName) {
        gunFire_thread = new GunFire_Thread(gunName);
        status = true;
        gunfire = getThreadByName(gunName);
    }

    public static Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) return t;
        }
        return null;
    }

    public static boolean isTreadAlive(String name) {

        return getThreadByName(name) != null;
    }

    public static void StopGunFire(String name) {
        if (isTreadAlive(name)) {
            try {
                gunFire_thread.running = false;
                gunFire_thread.stopFire();

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
