package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.DATA;

import com.redsparkle.api.utils.gui.GuiButtonExtFallout;
import com.redsparkle.foe.Init.ConfigInit;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by hoijima on 09.05.17.
 */
@SideOnly(Side.CLIENT)
public class DataGui {
    public final static GuiButtonExtFallout World_Map = new GuiButtonExtFallout(30,
            0,
            0,
            0,
            0, "World Map");
    public final static GuiButtonExtFallout Misc = new GuiButtonExtFallout(31,
            0,
            0,
            0,
            0, "Misc");
    public final static GuiButtonExtFallout Radio = new GuiButtonExtFallout(32,
            0,
            0,
            0,
            0, "Radio");
    public final static GuiButtonExtFallout[] buttonsDATAmain = new GuiButtonExtFallout[]{
            World_Map, Misc, Radio
    };
    public final static GuiButtonExtFallout RadioWasteland = new GuiButtonExtFallout(33,
            0,
            0,
            0,
            0, "Wasteland Radio");
    public final static GuiButtonExtFallout RadioManeStream = new GuiButtonExtFallout(34,
            0,
            0,
            0,
            0, ConfigInit.Radio1Name);
    public final static GuiButtonExtFallout RadioBrony = new GuiButtonExtFallout(335,
            0,
            0,
            0,
            0, ConfigInit.Radio2Name);
    public final static GuiButtonExtFallout RadioButtonStop = new GuiButtonExtFallout(36,
            0,
            0,
            0,
            0, "Radio STOP!");
    public final static GuiButtonExtFallout RadioVolumeUp = new GuiButtonExtFallout(37,
            0,
            0,
            0,
            0, "Volume +");
    public final static GuiButtonExtFallout RadioVolumeDown = new GuiButtonExtFallout(38,
            0,
            0,
            0,
            0, "Volume -");
    public final static GuiButtonExtFallout[] buttonsDATARadio = new GuiButtonExtFallout[]{
            RadioWasteland,
            RadioManeStream,
            RadioBrony, RadioButtonStop, RadioVolumeUp, RadioVolumeDown
    };
}
