package com.redsparkle.foe.Init;

import com.sun.media.jfxmedia.logging.Logger;

/**
 * Created by hoijima on 14.12.16.
 */
public class StartUpCommon {

    public static void preInitCommon() {
        ConfigInit.preInit();

        Logger.logMsg(Logger.INFO, "Radio config");
        Logger.logMsg(Logger.INFO, ConfigInit.Radio1Name);
        Logger.logMsg(Logger.INFO, ConfigInit.Radio1URL);
        Logger.logMsg(Logger.INFO, ConfigInit.Radio2Name);
        Logger.logMsg(Logger.INFO, ConfigInit.Radio2URL);


    }

    public static void InitCommon() {

    }

    public static void postInitCommon() {
    }
}
