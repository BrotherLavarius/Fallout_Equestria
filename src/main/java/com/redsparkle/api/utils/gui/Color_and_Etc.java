package com.redsparkle.api.utils.gui;

/**
 * Created by hoijima on 18.09.17.
 */
public class Color_and_Etc {
    public static int rawColorFromRGB(int red, int green, int blue) {
        int rgb = Math.max(Math.min(0xFF, red), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, green), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, blue), 0);
        return rgb;
    }
}
