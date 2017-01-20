package com.redsparkle.foe.Init;

import com.redsparkle.foe.items.guns.LaserPistol;
import com.redsparkle.foe.items.guns.TenMM;
import com.redsparkle.foe.items.guns.ammo.LaserWeapons.Battery;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMammo;
import com.redsparkle.foe.items.meds.RadAway;
import com.redsparkle.foe.items.meds.RadX;
import com.redsparkle.foe.items.utility.PipBuck;
import com.redsparkle.foe.utils.GlobalNames;
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


    // AMMO and CLIPS
    public static TenMMammo tenMMAmmo;
    public static TenMMClip tenMMClip;

    public static Battery battery;

    // GUNS
    public static TenMM tenMM;
    public static LaserPistol laserPistol;

    // PLACEHOLDER
    //public static TenMMbullet tenMMbullet;



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
        pipbuck = (PipBuck) (new PipBuck().setUnlocalizedName("Pip Buck 3000"));
        pipbuck.setRegistryName(GlobalNames.Pipbuck);
        GameRegistry.register(pipbuck);




        //----------------------PLACEHOLDER--------------------
/*
        tenMMbullet = (TenMMbullet) (new TenMMbullet().setUnlocalizedName("bullet10mm"));
        tenMMbullet.setRegistryName("bullet10mm");
        GameRegistry.register(tenMMbullet);
*/

    }

    public static void InitCommon() {
    }

    public static void postInitCommon() {
    }
}
