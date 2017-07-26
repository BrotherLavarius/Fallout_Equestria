package com.redsparkle.api.utils;
/**
 * Created by hoijima on 09.05.17.
 */
public class ScreenGrid {
    public static int XCoordS, YCoordS, XCoordE, YCoordE = 0;
    public static int XCoordStart(int ScreenWight, float XStartGridPercent) {
        XCoordS = (int) ((ScreenWight * (0.01 * XStartGridPercent)));
        return XCoordS;
    }
    public static int YCoordStart(int ScreenHeight, float YStartGridPercent) {
        YCoordS = (int) ((ScreenHeight * (0.01 * YStartGridPercent)));
        return YCoordS;
    }
    public static int XCoordEnd(int ScreenWight, float XEndGridPercent) {
        XCoordE = (int) (ScreenWight - (ScreenWight * (0.01 * XEndGridPercent)));
        return XCoordE;
    }
    public static int YCoordEndt(int ScreenHeight, float YStopGridPercent) {
        YCoordE = (int) (ScreenHeight - (ScreenHeight * (0.01 * YStopGridPercent)));
        return YCoordE;
    }
}
