package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.STATS;

import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.GuiButtonExt;

/**
 * Created by hoijima on 09.05.17.
 */
public class StatsGui {
    public static String[] skills = {
            "STR", "PER", "END", "CHA", "INT", "AGI", "LUC"
    };
    public static GuiButtonExt STATUS = new GuiButtonExt(5,
            0,
            0,
            0,
            0, "Status");
    public static GuiButtonExt SPECHIAL = new GuiButtonExt(6,
            0,
            0,
            0,
            0, "S.P.E.C.H.I.A.L");
    public static GuiButtonExt SKILLS = new GuiButtonExt(7,
            0,
            0,
            0,
            0, "Skills");
    public static GuiButtonExt PERKS = new GuiButtonExt(8,
            0,
            0,
            0,
            0, "Perks");
    public static final GuiButtonExt[] buttonsSTATNavigation = new GuiButtonExt[]{
            STATUS, SPECHIAL, SKILLS, PERKS

    };
    public static GuiButtonExt CND = new GuiButtonExt(10,
            0,
            0,
            0,
            0, "CND");
    public static GuiButtonExt RAD = new GuiButtonExt(11,
            0,
            0,
            0,
            0, "RAD");
    public static GuiButtonExt EFF = new GuiButtonExt(12,
            0,
            0,
            0,
            0, "EFF");
    public static GuiButtonExt H2O = new GuiButtonExt(13,
            0,
            0,
            0,
            0, "H2O");
    public static GuiButtonExt FOD = new GuiButtonExt(14,
            0,
            0,
            0,
            0, "FOD");
    public static GuiButtonExt SLP = new GuiButtonExt(15,
            0,
            0,
            0,
            0, "SLP");
    public static final GuiButtonExt[] buttonsSTATmain = new GuiButtonExt[]{
            CND, RAD, EFF, H2O, FOD, SLP

    };
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
    public static GuiButtonExt World_Map = new GuiButtonExt(30,
            0,
            0,
            0,
            0, "World Map");
    public static GuiButtonExt Misc = new GuiButtonExt(31,
            0,
            0,
            0,
            0, "Misc");
    public static GuiButtonExt Radio = new GuiButtonExt(32,
            0,
            0,
            0,
            0, "Radio");
    public static final GuiButtonExt[] buttonsDATAmain = new GuiButtonExt[]{
            World_Map, Misc, Radio
    };
    static Minecraft mc = Minecraft.getMinecraft();
    public static Integer[] spechials = {
            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght(),
            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getPerception(),
            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getEndurance(),
            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getCharisma(),
            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getIntelligence(),
            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getAgility(),
            mc.player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getLuck()
    };
    public static Integer[] playerParams = {
            mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel(),
            mc.player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress()

    };


}