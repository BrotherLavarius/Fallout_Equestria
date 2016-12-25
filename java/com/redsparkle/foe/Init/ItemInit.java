package com.redsparkle.foe.Init;

import com.redsparkle.foe.items.meds.RadAway;
import com.redsparkle.foe.items.meds.RadX;
import com.redsparkle.foe.items.utility.PipBuck;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by hoijima on 14.12.16.
 */
public class ItemInit {
    public static RadX radx;
    public static RadAway radAway;
    public static PipBuck pipbuck;
    //public static ItemSimple itemSimple;  // this holds the unique instance of your block

    public static void preInitCommon()
    {
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
        radAway = (RadAway)(new RadAway().setUnlocalizedName(GlobalNames.RadAway));
        radAway.setRegistryName(GlobalNames.RadAway);
        GameRegistry.register(radAway);

        radx = (RadX)(new RadX().setUnlocalizedName(GlobalNames.RadX));
        radx.setRegistryName(GlobalNames.RadX);
        GameRegistry.register(radx);
        //
        pipbuck = (PipBuck)(new PipBuck().setUnlocalizedName("Pip Buck 3000"));
        pipbuck.setRegistryName(GlobalNames.Pipbuck);
        GameRegistry.register(pipbuck);

    }
    public static void InitCommon()
    {}
    public static void postInitCommon()
    {}
}
