package com.redsparkle.foe.Init;

import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.block.interractable.DesktopTerminal;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.events.*;
import com.redsparkle.foe.gui.PipBuckGui;
import com.redsparkle.foe.gui.RadsOverlay;
import com.redsparkle.foe.main;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 23.09.16.
 */
public class ClientOnlyStartup {
    public static void preInitClientOnly()
    {
        final CreativeTabs Fallout_ammo = InitCreativeTabs.Fallout_ammo;
        final CreativeTabs Fallout_blocks = InitCreativeTabs.Fallout_blocks;
        final CreativeTabs Fallout_guns = InitCreativeTabs.Fallout_guns;
        final CreativeTabs Fallout_meds = InitCreativeTabs.Fallout_meds;
        final CreativeTabs Fallout_stats_blocks = InitCreativeTabs.Fallout_stats_blocks;
        //final CreativeTabs Fallout_Util = InitCreativeTabs.Fallout_Utils;
        SoundInit.registerSounds();
        OBJLoader.INSTANCE.addDomain(GlobalNames.Domain);
        Item SPCmachine = Item.getItemFromBlock(SparkleColaMachineBlock.instance);
        ModelLoader.setCustomModelResourceLocation(SPCmachine,0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.SPCmachine,"inventory"));

        Item Dterminal = Item.getItemFromBlock(DesktopTerminal.instance);
        ModelLoader.setCustomModelResourceLocation(SPCmachine,0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Terminal,"inventory"));

        //ITEMS SECTION#########################################

        //---------------------UTILITY--------------------------
        final int DEFAULT_ITEM_SUBTYPE = 0;
        ModelLoader.setCustomModelResourceLocation(ItemInit.pipbuck, DEFAULT_ITEM_SUBTYPE,  new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Pipbuck, "inventory"));

        //---------------------MEDS--------------------------
        ModelLoader.setCustomModelResourceLocation(ItemInit.radAway, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.RadAway, "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemInit.radx, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.RadX, "inventory"));
    }

    private static PipBuckGui pipBuckGui;
    private static RadsOverlay radsGui;
    public static void initClientOnly()
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(SparkleColaMachineBlock.instance),0,new ModelResourceLocation(MODID + ":" + GlobalNames.SPCmachine));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(RadiationBlock.instance), 0, new ModelResourceLocation(MODID + ":" + GlobalNames.RadBlock));
        pipBuckGui = new PipBuckGui(Minecraft.getMinecraft());
        MinecraftForge.EVENT_BUS.register(new EventHandlerOverlayPipBuck(pipBuckGui));
    }


    public static void postInitClientOnly()
    {
  /* Here, we register the event handler that modifies the overlay. Since
   * the overlay is a GUI element, and the GUI only exists on the client side,
   * we only register this event handler on the client side.
   */
        //radsGui = new RadsOverlay(Minecraft.getMinecraft());
        //MinecraftForge.EVENT_BUS.register(new EventHandlerOverlayRads(radsGui));

    }
}
