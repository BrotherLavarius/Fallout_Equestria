package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.ITEMS;

import com.redsparkle.api.utils.gui.GuiButtonExtFallout;

/**
 * Created by hoijima on 09.05.17.
 */
public class InventoryGui {
    public static GuiButtonExtFallout WEAPONS = new GuiButtonExtFallout(20,
            0,
            0,
            0,
            0, "Weapons");
    public static GuiButtonExtFallout Apparel = new GuiButtonExtFallout(21,
            0,
            0,
            0,
            0, "Apparel");
    public static GuiButtonExtFallout Aid = new GuiButtonExtFallout(22,
            0,
            0,
            0,
            0, "Aid");
    public static GuiButtonExtFallout MiscInv = new GuiButtonExtFallout(23,
            0,
            0,
            0,
            0, "Misc");
    public static GuiButtonExtFallout Ammo = new GuiButtonExtFallout(24,
            0,
            0,
            0,
            0, "Ammo");
    public static GuiButtonExtFallout Mod = new GuiButtonExtFallout(25,
            0,
            0,
            0,
            0, "Mod");
    public static GuiButtonExtFallout Repair = new GuiButtonExtFallout(26,
            0,
            0,
            0,
            0, "Repair");
    public static final GuiButtonExtFallout[] buttonsINVmain = new GuiButtonExtFallout[]{
            WEAPONS, Apparel, Aid, Ammo, MiscInv, Mod, Repair

    };
}
