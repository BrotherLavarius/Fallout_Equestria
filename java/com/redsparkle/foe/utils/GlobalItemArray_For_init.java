package com.redsparkle.foe.utils;

import com.redsparkle.foe.items.food.old.*;
import com.redsparkle.foe.items.guns.FourTenMM;
import com.redsparkle.foe.items.guns.LaserPistol;
import com.redsparkle.foe.items.guns.TenMM;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMClip;
import com.redsparkle.foe.items.guns.ammo.FourTenMM.FourTenMMammo;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMammo;
import com.redsparkle.foe.items.guns.inits.itembullet;
import com.redsparkle.foe.items.meds.HealPotions.DarkHealPotion;
import com.redsparkle.foe.items.meds.HealPotions.PurpleHealPotion;
import com.redsparkle.foe.items.meds.HealPotions.RedHealPotion;
import com.redsparkle.foe.items.meds.HealPotions.WhiteHealpotion;
import com.redsparkle.foe.items.meds.RadAway;
import com.redsparkle.foe.items.meds.RadX;
import com.redsparkle.foe.items.utility.AdvancedEnviropmentMonitor;
import com.redsparkle.foe.items.utility.LvlingCrystall;
import com.redsparkle.foe.items.utility.PipBuck;
import net.minecraft.item.Item;

/**
 * Created by hoijima on 23.06.17.
 */
public class GlobalItemArray_For_init {


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
            GlobalNames.redHealPotion,
            GlobalNames.whiteHealpotion,
            GlobalNames.purpleHealPotion,
            GlobalNames.darkHealPotion,
            //---------------------AMMO--------------------------
            GlobalNames.TenMMAmmo,
            GlobalNames.TenMMClip,
            GlobalNames.Battery,
            GlobalNames.FourTenMMAmmo,
            GlobalNames.FourTenMMClip,
            //---------------------GUNS--------------------------

            GlobalNames.TenMM,
            GlobalNames.LaserPistol,
            GlobalNames.FourTenMM,
            //---------------------ARMOR--------------------------

            //---------------------PLACEHOLDER--------------------
            "bullet"
    };
    // MEDS
    public static RadX radx;
    public static RadAway radAway;
    public static RedHealPotion redHealPotion;
    public static WhiteHealpotion whiteHealpotion;
    public static PurpleHealPotion purpleHealPotion;
    public static DarkHealPotion darkHealPotion;
    // UTILITY
    public static PipBuck pipbuck;
    public static LvlingCrystall lvlingCrystall;
    public static AdvancedEnviropmentMonitor aem;
    // FOOD
    public static Potato_Crisps potato_crisps;
    public static Blamko_MacCheese blamko_macCheese;
    public static Dandy_Boy_Apples dandy_boy_apples;
    public static GumDrops gumDrops;
    public static Sugar_Bombs sugar_bombs;
    public static Fancy_mare_Snack_Cakes fancy_mare_snack_cakes;
    // AMMO and CLIPS
    public static TenMMammo tenMMAmmo;
    public static TenMMClip tenMMClip;
    public static FourTenMMammo fourTenMMammo;
    public static FourTenMMClip fourTenMMClip;
    public static Battery battery;
    // GUNS
    public static TenMM tenMM;
    public static FourTenMM fourTenMM;

    // PLACEHOLDER
    public static LaserPistol laserPistol;
    public static itembullet itb;
    public static final Item[] AllInit = new Item[]{

            //---------------------UTILITY--------------------------

            pipbuck,
            lvlingCrystall,
            aem,
            //--------------FOOD--------------------------
            blamko_macCheese,
            dandy_boy_apples,
            fancy_mare_snack_cakes,
            gumDrops,
            potato_crisps,
            sugar_bombs,
            //--------------MEDS--------------------------

            radAway,
            radx,

            redHealPotion,
            whiteHealpotion,
            purpleHealPotion,
            darkHealPotion,
            //--------------AMMO--------------------------

            tenMMAmmo,
            tenMMClip,
            battery,
            fourTenMMammo,
            fourTenMMClip,
            //--------------GUNS--------------------------

            tenMM,
            laserPistol,
            fourTenMM,

            //--------------ARMOR--------------------------

            //--------------PLACEHOLDER--------------------
            itb


    };
    public static Item[] obj = new Item[]{
            new PipBuck(),
            new LvlingCrystall(),
            new AdvancedEnviropmentMonitor(),

            new Blamko_MacCheese(),
            new Dandy_Boy_Apples(),
            new GumDrops(),
            new Potato_Crisps(),
            new Fancy_mare_Snack_Cakes(),
            new Sugar_Bombs(),

            new RadAway(),
            new RadX(),

            new RedHealPotion(),
            new WhiteHealpotion(),
            new PurpleHealPotion(),
            new DarkHealPotion(),


            new TenMMammo(),
            new TenMMClip(),
            new Battery(),
            new FourTenMMammo(),
            new FourTenMMClip(),

            new TenMM(),
            new LaserPistol(),
            new FourTenMM(),

            new itembullet()
    };

}

