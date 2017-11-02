package com.redsparkle.api.utils;

import net.minecraftforge.fml.relauncher.FMLRelaunchLog;
import org.apache.logging.log4j.Level;

/**
 * Created by hoijima on 07.07.17.
 */
public class FOELog {
    public static void info(String message) {
        FMLRelaunchLog.log(GlobalNames.Domain, Level.INFO, message);
    }

    public static void severe(String message) {
        FMLRelaunchLog.log(GlobalNames.Domain, Level.ERROR, message);
    }

    public static void debug(String message) {
        FMLRelaunchLog.log(GlobalNames.Domain, Level.INFO, "Debug: " + message);
    }

    public static void exception(Exception e) {
        FMLRelaunchLog.log(GlobalNames.Domain, Level.ERROR, e.getMessage());
    }
}
