package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.STATS;

import com.redsparkle.api.utils.gui.GuiButtonExtFallout;

/**
 * Created by hoijima on 09.05.17.
 */
public class StatsGui {
    public static String[] spechialName = {
            "STR", "PER", "END", "CHA", "INT", "AGI", "LUC"
    };
    public static String[] skillsNames = {
            "Big Guns:",
            "Small Guns:",
            "Energy Weapons:",
            "Explosives:",
            "MeleeWeapons:",
            "Unarmed:",
            "Medicine:",
            "Lockpick:",
            "Repair:",
            "Science:",
            "Sneak:",
            "Barter:"

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


}