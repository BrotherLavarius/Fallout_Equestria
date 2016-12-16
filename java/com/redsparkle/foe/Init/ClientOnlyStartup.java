package com.redsparkle.foe.Init;

import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.events.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 23.09.16.
 */
public class ClientOnlyStartup {
    public static void preInitClientOnly()
    {
        OBJLoader.INSTANCE.addDomain(MODID.toLowerCase());
        final CreativeTabs Fallout_ammo = InitCreativeTabs.Fallout_ammo;
        final CreativeTabs Fallout_blocks = InitCreativeTabs.Fallout_blocks;
        final CreativeTabs Fallout_guns = InitCreativeTabs.Fallout_guns;
        final CreativeTabs Fallout_meds = InitCreativeTabs.Fallout_meds;
        final CreativeTabs Fallout_stats_blocks = InitCreativeTabs.Fallout_stats_blocks;
        //final CreativeTabs Fallout_Util = InitCreativeTabs.Fallout_Utils;
        MinecraftForge.EVENT_BUS.register(new GuiRenderHandler());



    }


    public static void initClientOnly()
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(Item.getItemFromBlock(RadiationBlock.instance), 0, new ModelResourceLocation(MODID.toLowerCase() + ":" + RadiationBlock.name, "inventory"));
    }


    public static void postInitClientOnly()
    {
  /* Here, we register the event handler that modifies the overlay. Since
   * the overlay is a GUI element, and the GUI only exists on the client side,
   * we only register this event handler on the client side.
   */

    }
}
