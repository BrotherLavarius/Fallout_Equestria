package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.STATS;

import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.utils.gui.GuiButtonExtFallout;
import net.minecraft.client.Minecraft;

/**
 * Created by hoijima on 09.05.17.
 */
public class StatsGui {
    public static String[] skills = {
            "STR", "PER", "END", "CHA", "INT", "AGI", "LUC"
    };
    public static GuiButtonExtFallout STATUS = new GuiButtonExtFallout(5,
            0,
            0,
            0,
            0, "Status");
    public static GuiButtonExtFallout SPECIAL = new GuiButtonExtFallout(6,
            0,
            0,
            0,
            0, "SPECIAL");
    public static GuiButtonExtFallout SKILLS = new GuiButtonExtFallout(7,
            0,
            0,
            0,
            0, "Skills");
    public static GuiButtonExtFallout PERKS = new GuiButtonExtFallout(8,
            0,
            0,
            0,
            0, "Perks");
    public static final GuiButtonExtFallout[] buttonsSTATNavigation = new GuiButtonExtFallout[]{
            STATUS, SPECIAL, SKILLS, PERKS

    };
    public static GuiButtonExtFallout CND = new GuiButtonExtFallout(10,
            0,
            0,
            0,
            0, "CND");
    public static GuiButtonExtFallout RAD = new GuiButtonExtFallout(11,
            0,
            0,
            0,
            0, "RAD");
    public static GuiButtonExtFallout EFF = new GuiButtonExtFallout(12,
            0,
            0,
            0,
            0, "EFF");
    public static GuiButtonExtFallout H2O = new GuiButtonExtFallout(13,
            0,
            0,
            0,
            0, "H2O");
    public static GuiButtonExtFallout FOD = new GuiButtonExtFallout(14,
            0,
            0,
            0,
            0, "FOD");
    public static GuiButtonExtFallout SLP = new GuiButtonExtFallout(15,
            0,
            0,
            0,
            0, "SLP");
    public static final GuiButtonExtFallout[] buttonsSTATmain = new GuiButtonExtFallout[]{
            CND, RAD, EFF, H2O, FOD, SLP

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