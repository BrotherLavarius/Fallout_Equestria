package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.ITEMS;

import net.minecraftforge.fml.client.config.GuiButtonExt;

/**
 * Created by hoijima on 09.05.17.
 */
public class InventoryGui {
    public static GuiButtonExt WEAPONS = new GuiButtonExt(20,
            0,
            0,
            0,
            0, "Weapons");
    public static GuiButtonExt Apparel = new GuiButtonExt(21,
            0,
            0,
            0,
            0, "Apparel");
    public static GuiButtonExt Aid = new GuiButtonExt(22,
            0,
            0,
            0,
            0, "Aid");
    public static GuiButtonExt MiscInv = new GuiButtonExt(23,
            0,
            0,
            0,
            0, "Misc");
    public static GuiButtonExt Ammo = new GuiButtonExt(24,
            0,
            0,
            0,
            0, "Ammo");
    public static GuiButtonExt Mod = new GuiButtonExt(25,
            0,
            0,
            0,
            0, "Ammo");
    public static GuiButtonExt Repair = new GuiButtonExt(26,
            0,
            0,
            0,
            0, "Repair");
    public static final GuiButtonExt[] buttonsINVmain = new GuiButtonExt[]{
            WEAPONS, Apparel, Aid, Ammo, MiscInv, Mod, Repair

    };
}
