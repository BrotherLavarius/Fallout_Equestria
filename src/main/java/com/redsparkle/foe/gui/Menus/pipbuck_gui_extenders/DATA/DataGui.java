package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.DATA;

import com.redsparkle.api.utils.gui.GuiButtonExtFallout;
import com.redsparkle.foe.Init.ConfigInit;

/**
 * Created by hoijima on 09.05.17.
 */
public class DataGui {
    public static GuiButtonExtFallout World_Map = new GuiButtonExtFallout(30,
            0,
            0,
            0,
            0, "World Map");
    public static GuiButtonExtFallout Misc = new GuiButtonExtFallout(31,
            0,
            0,
            0,
            0, "Misc");
    public static GuiButtonExtFallout Radio = new GuiButtonExtFallout(32,
            0,
            0,
            0,
            0, "Radio");
    public static final GuiButtonExtFallout[] buttonsDATAmain = new GuiButtonExtFallout[]{
            World_Map, Misc, Radio
    };
    public static GuiButtonExtFallout RadioWasteland = new GuiButtonExtFallout(33,
            0,
            0,
            0,
            0, "Wasteland Radio");
    public static GuiButtonExtFallout RadioManeStream = new GuiButtonExtFallout(34,
            0,
            0,
            0,
            0, ConfigInit.Radio1Name);
    public static GuiButtonExtFallout RadioBrony = new GuiButtonExtFallout(335,
            0,
            0,
            0,
            0, ConfigInit.Radio2Name);
    public static GuiButtonExtFallout RadioButtonStop = new GuiButtonExtFallout(36,
            0,
            0,
            0,
            0, "Radio STOP!");
    public static GuiButtonExtFallout RadioVolumeUp = new GuiButtonExtFallout(37,
            0,
            0,
            0,
            0, "Volume +");
    public static GuiButtonExtFallout RadioVolumeDown = new GuiButtonExtFallout(38,
            0,
            0,
            0,
            0, "Volume -");
    public static final GuiButtonExtFallout[] buttonsDATARadio = new GuiButtonExtFallout[]{
            RadioWasteland,
            RadioManeStream,
            RadioBrony, RadioButtonStop, RadioVolumeUp, RadioVolumeDown
    };
}
