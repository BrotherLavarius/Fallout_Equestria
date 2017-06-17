package com.redsparkle.foe.utils;

import com.redsparkle.foe.Init.ItemInit;
import net.minecraft.item.Item;

/**
 * Created by hoijima on 08.06.17.
 */
public class GlobalItemArray {

    public static final Item[] items = new Item[]{

            //---------------------UTILITY--------------------------

            ItemInit.pipbuck,
            ItemInit.lvlingCrystall,
            ItemInit.aem,
            //---------------------FOOD--------------------------
            ItemInit.blamko_macCheese,
            ItemInit.dandy_boy_apples,
            ItemInit.fancy_mare_snack_cakes,
            ItemInit.gumDrops,
            ItemInit.potato_crisps,
            ItemInit.sugar_bombs,
            //---------------------MEDS--------------------------

            ItemInit.radAway,
            ItemInit.radx,
            //---------------------AMMO--------------------------

            ItemInit.tenMMAmmo,
            ItemInit.tenMMClip,
            ItemInit.battery,
            //---------------------GUNS--------------------------

            ItemInit.tenMM,
            ItemInit.laserPistol,
            //---------------------ARMOR--------------------------

            ItemInit.t60head,
            ItemInit.t60body,
            ItemInit.t50head,
            ItemInit.t50body,
            ItemInit.t40head,
            ItemInit.t40body,
            ItemInit.t60legs,
            ItemInit.t50legs,
            ItemInit.t40legs,

            //---------------------PLACEHOLDER--------------------

            ItemInit.tenMMbullet
    };
    public static final String[] ItemNames = new String[]{
            //---------------------UTILITY--------------------------
            GlobalNames.PipbuckTT,
            GlobalNames.LevelingCrystall,
            GlobalNames.AEM,
            //---------------------FOOD--------------------------
            GlobalNames.blamko_macCheese,
            GlobalNames.dandy_boy_apples,
            GlobalNames.Fancy_mare_Snack_Cakes,
            GlobalNames.gumDrops,
            GlobalNames.potato_crisps,
            GlobalNames.sugar_bombs,
            //---------------------MEDS--------------------------

            GlobalNames.RadAway,
            GlobalNames.RadX,
            //---------------------AMMO--------------------------
            GlobalNames.TenMMAmmo,
            GlobalNames.TenMMClip,
            GlobalNames.Battery,
            //---------------------GUNS--------------------------

            GlobalNames.TenMM,
            GlobalNames.LaserPistol,
            //---------------------ARMOR--------------------------

            GlobalNames.T60Head,
            GlobalNames.T60Body,
            GlobalNames.T50Head,
            GlobalNames.T50Body,
            GlobalNames.T40Head,
            GlobalNames.T40Body,
            GlobalNames.T60Legs,
            GlobalNames.T50Legs,
            GlobalNames.T40Legs,
            //---------------------PLACEHOLDER--------------------

            GlobalNames.TenMMbullet
    };

}
