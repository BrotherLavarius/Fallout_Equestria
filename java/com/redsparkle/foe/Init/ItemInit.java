package com.redsparkle.foe.Init;

import com.redsparkle.foe.items.armor.powered.*;
import com.redsparkle.foe.items.guns.LaserPistol;
import com.redsparkle.foe.items.guns.TenMM;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMammo;
import com.redsparkle.foe.items.guns.bullets.TenMMbullet;
import com.redsparkle.foe.items.meds.RadAway;
import com.redsparkle.foe.items.meds.RadX;
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


    // UTILITY
    public static PipBuck pipbuck;
    public static LvlingCrystall lvlingCrystall;


    // AMMO and CLIPS
    public static TenMMammo tenMMAmmo;
    public static TenMMClip tenMMClip;

    public static Battery battery;

    // GUNS
    public static TenMM tenMM;
    public static LaserPistol laserPistol;

    // PLACEHOLDER
    public static TenMMbullet tenMMbullet;

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


        //----------------------GUNS--------------------

        tenMM = (TenMM) (new TenMM().setUnlocalizedName(GlobalNames.TenMM));
        tenMM.setRegistryName(GlobalNames.TenMM);
        GameRegistry.register(tenMM);

        laserPistol = (LaserPistol) (new LaserPistol().setUnlocalizedName(GlobalNames.LaserPistol));
        laserPistol.setRegistryName(GlobalNames.LaserPistol);
        GameRegistry.register(laserPistol);

        //----------------------UTILITY-----------------
        pipbuck = (PipBuck) (new PipBuck().setUnlocalizedName(GlobalNames.PipbuckTT));
        pipbuck.setRegistryName(GlobalNames.PipbuckTT);
        GameRegistry.register(pipbuck);

        lvlingCrystall = (LvlingCrystall) (new LvlingCrystall().setUnlocalizedName(GlobalNames.LevelingCrystall));
        lvlingCrystall.setRegistryName(GlobalNames.LevelingCrystall);
        GameRegistry.register(lvlingCrystall);

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

        tenMMbullet = (TenMMbullet) (new TenMMbullet().setUnlocalizedName(GlobalNames.TenMMbullet));
        tenMMbullet.setRegistryName(GlobalNames.TenMMbullet);
        GameRegistry.register(tenMMbullet);
    }

    public static void InitCommon() {
    }

    public static void postInitCommon() {
    }
}
