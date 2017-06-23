package com.redsparkle.foe.Init;

import com.redsparkle.foe.items.armor.powered.*;
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
import com.redsparkle.foe.utils.Armor_material;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by hoijima on 14.12.16.
 */
public class ItemInit {

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

    public static LaserPistol laserPistol;

    // PLACEHOLDER

    public static itembullet itb;
    //ARMOR
    public static t40head t40head;
    public static t40body t40body;
    public static t40legs t40legs;

    public static t50head t50head;
    public static t50body t50body;
    public static t50legs t50legs;

    public static t60head t60head;
    public static t60body t60body;
    public static t60legs t60legs;

    //public static ItemSimple itemSimple;  // this holds the unique instance of your block

    public static void preInitCommon() {
        // each instance of your item should have two names:
        // 1) a registry name that is used to uniquely identify this item.  Should be unique within your mod.  use lower case.
        // 2) an 'unlocalised name' that is used to retrieve the text name of your item in the player's language.  For example-
        //    the unlocalised name might be "water", which is printed on the user's screen as "Wasser" in German or
        //    "aqua" in Italian.
        //
        //    Multiple items can have the same unlocalised name - for example
        //  +----RegistryName-------+----UnlocalisedName----+
        //  |  burning_candle       +       candle          |
        //  |  extinguished_candle  +       candle          |
        //  +-----------------------+-----------------------+
        //
        //----------------------MEDS-----------------
        radAway = (RadAway) (new RadAway().setUnlocalizedName(GlobalNames.RadAway));
        radAway.setRegistryName(GlobalNames.RadAway);
        GameRegistry.register(radAway);

        radx = (RadX) (new RadX().setUnlocalizedName(GlobalNames.RadX));
        radx.setRegistryName(GlobalNames.RadX);
        GameRegistry.register(radx);




        redHealPotion = (RedHealPotion) (new RedHealPotion().setUnlocalizedName(GlobalNames.redHealPotion));
        redHealPotion.setRegistryName(GlobalNames.redHealPotion);
        GameRegistry.register(redHealPotion);

        whiteHealpotion = (WhiteHealpotion) (new WhiteHealpotion().setUnlocalizedName(GlobalNames.whiteHealpotion));
        whiteHealpotion.setRegistryName(GlobalNames.whiteHealpotion);
        GameRegistry.register(whiteHealpotion);

        purpleHealPotion = (PurpleHealPotion) (new PurpleHealPotion().setUnlocalizedName(GlobalNames.purpleHealPotion));
        purpleHealPotion.setRegistryName(GlobalNames.purpleHealPotion);
        GameRegistry.register(purpleHealPotion);

        darkHealPotion = (DarkHealPotion) (new DarkHealPotion().setUnlocalizedName(GlobalNames.darkHealPotion));
        darkHealPotion.setRegistryName(GlobalNames.darkHealPotion);
        GameRegistry.register(darkHealPotion);

        //----------------------FOOD-----------------
        potato_crisps = (Potato_Crisps) (new Potato_Crisps().setUnlocalizedName(GlobalNames.potato_crisps));
        potato_crisps.setRegistryName(GlobalNames.potato_crisps);
        GameRegistry.register(potato_crisps);


        blamko_macCheese = (Blamko_MacCheese) (new Blamko_MacCheese().setUnlocalizedName(GlobalNames.blamko_macCheese));
        blamko_macCheese.setRegistryName(GlobalNames.blamko_macCheese);
        GameRegistry.register(blamko_macCheese);

        dandy_boy_apples = (Dandy_Boy_Apples) (new Dandy_Boy_Apples().setUnlocalizedName(GlobalNames.dandy_boy_apples));
        dandy_boy_apples.setRegistryName(GlobalNames.dandy_boy_apples);
        GameRegistry.register(dandy_boy_apples);

        gumDrops = (GumDrops) (new GumDrops().setUnlocalizedName(GlobalNames.gumDrops));
        gumDrops.setRegistryName(GlobalNames.gumDrops);
        GameRegistry.register(gumDrops);

        sugar_bombs = (Sugar_Bombs) (new Sugar_Bombs().setUnlocalizedName(GlobalNames.sugar_bombs));
        sugar_bombs.setRegistryName(GlobalNames.sugar_bombs);
        GameRegistry.register(sugar_bombs);

        fancy_mare_snack_cakes = (Fancy_mare_Snack_Cakes) (new Fancy_mare_Snack_Cakes().setUnlocalizedName(GlobalNames.Fancy_mare_Snack_Cakes));
        fancy_mare_snack_cakes.setRegistryName(GlobalNames.Fancy_mare_Snack_Cakes);
        GameRegistry.register(fancy_mare_snack_cakes);

        //----------------------AMMO-----------------
        tenMMAmmo = (TenMMammo) (new TenMMammo().setUnlocalizedName(GlobalNames.TenMMAmmo));
        tenMMAmmo.setRegistryName(GlobalNames.TenMMAmmo);
        GameRegistry.register(tenMMAmmo);

        tenMMClip = (TenMMClip) (new TenMMClip().setUnlocalizedName(GlobalNames.TenMMClip));
        tenMMClip.setRegistryName(GlobalNames.TenMMClip);
        GameRegistry.register(tenMMClip);

        battery = (Battery) (new Battery().setUnlocalizedName(GlobalNames.Battery));
        battery.setRegistryName(GlobalNames.Battery);
        GameRegistry.register(battery);

        fourTenMMammo = (FourTenMMammo) (new FourTenMMammo().setUnlocalizedName(GlobalNames.FourTenMMAmmo));
        fourTenMMammo.setRegistryName(GlobalNames.FourTenMMAmmo);
        GameRegistry.register(fourTenMMammo);

        fourTenMMClip = (FourTenMMClip) (new FourTenMMClip().setUnlocalizedName(GlobalNames.FourTenMMClip));
        fourTenMMClip.setRegistryName(GlobalNames.FourTenMMClip);
        GameRegistry.register(fourTenMMClip);

        //----------------------GUNS--------------------

        tenMM = (TenMM) (new TenMM().setUnlocalizedName(GlobalNames.TenMM));
        tenMM.setRegistryName(GlobalNames.TenMM);
        GameRegistry.register(tenMM);

        laserPistol = (LaserPistol) (new LaserPistol().setUnlocalizedName(GlobalNames.LaserPistol));
        laserPistol.setRegistryName(GlobalNames.LaserPistol);
        GameRegistry.register(laserPistol);

        fourTenMM = (FourTenMM) (new FourTenMM().setUnlocalizedName(GlobalNames.FourTenMM));
        fourTenMM.setRegistryName(GlobalNames.FourTenMM);
        GameRegistry.register(fourTenMM);

        //----------------------UTILITY-----------------
        pipbuck = (PipBuck) (new PipBuck().setUnlocalizedName(GlobalNames.PipbuckTT));
        pipbuck.setRegistryName(GlobalNames.PipbuckTT);
        GameRegistry.register(pipbuck);

        lvlingCrystall = (LvlingCrystall) (new LvlingCrystall().setUnlocalizedName(GlobalNames.LevelingCrystall));
        lvlingCrystall.setRegistryName(GlobalNames.LevelingCrystall);
        GameRegistry.register(lvlingCrystall);

        aem = (AdvancedEnviropmentMonitor) (new AdvancedEnviropmentMonitor().setUnlocalizedName(GlobalNames.AEM));
        aem.setRegistryName(GlobalNames.AEM);
        GameRegistry.register(aem);

        //----------------------ARMOR-------------------

        t40head = (t40head) (new t40head(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName(GlobalNames.T40Head));
        t40head.setRegistryName(GlobalNames.T40Head);
        GameRegistry.register(t40head);

        t40body = (t40body) (new t40body(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName(GlobalNames.T40Body));
        t40body.setRegistryName(GlobalNames.T40Body);
        GameRegistry.register(t40body);


        t50head = (t50head) (new t50head(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName(GlobalNames.T50Head));
        t50head.setRegistryName(GlobalNames.T50Head);
        GameRegistry.register(t50head);

        t50body = (t50body) (new t50body(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName(GlobalNames.T50Body));
        t50body.setRegistryName(GlobalNames.T50Body);
        GameRegistry.register(t50body);


        t60head = (t60head) (new t60head(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName(GlobalNames.T60Head));
        t60head.setRegistryName(GlobalNames.T60Head);
        GameRegistry.register(t60head);

        t60body = (t60body) (new t60body(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName(GlobalNames.T60Body));
        t60body.setRegistryName(GlobalNames.T60Body);
        GameRegistry.register(t60body);


        t40legs = (t40legs) (new t40legs(Armor_material.T40_ARMOR, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName(GlobalNames.T40Legs));
        t40legs.setRegistryName(GlobalNames.T40Legs);
        GameRegistry.register(t40legs);

        t50legs = (t50legs) (new t50legs(Armor_material.T50_ARMOR, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName(GlobalNames.T50Legs));
        t50legs.setRegistryName(GlobalNames.T50Legs);
        GameRegistry.register(t50legs);

        t60legs = (t60legs) (new t60legs(Armor_material.T60_ARMOR, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName(GlobalNames.T60Legs));
        t60legs.setRegistryName(GlobalNames.T60Legs);
        GameRegistry.register(t60legs);

        //----------------------PLACEHOLDER--------------------
        itb = (itembullet) (new itembullet().setUnlocalizedName("bullet"));
        itb.setRegistryName("bullet");
        GameRegistry.register(itb);
    }

    public static void InitCommon() {
    }

    public static void postInitCommon() {
    }
}
