package com.redsparkle.foe.Init;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.events.character.EventPlayerRenders;
import com.redsparkle.foe.events.gui.EventHandlerOverlayAEM;
import com.redsparkle.foe.events.gui.EventHandlerOverlayPipBuck;
import com.redsparkle.foe.events.gui.EventPlayerGuiHandler;
import com.redsparkle.foe.gui.Overlays.PipBuckOverlay;
import com.redsparkle.foe.items.guns.inits.EntityBullet;
import com.redsparkle.foe.items.guns.inits.ModelBullet;
import com.redsparkle.foe.items.guns.inits.RenderBullet;
import com.redsparkle.foe.utils.GlobalBlockArray;
import com.redsparkle.foe.utils.GlobalItemArray;
import com.redsparkle.foe.utils.GlobalNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import static com.redsparkle.foe.main.MODID;

/**
 * Created by hoijima on 23.09.16.
 */
public class ClientOnlyStartup {
    private static PipBuckOverlay pipBuckGui;

    public static void preInitClientOnly() {
        final CreativeTabs Fallout_ammo = InitCreativeTabs.Fallout_ammo;
        final CreativeTabs Fallout_blocks = InitCreativeTabs.Fallout_blocks;
        final CreativeTabs Fallout_guns = InitCreativeTabs.Fallout_guns;
        final CreativeTabs Fallout_food = InitCreativeTabs.Fallout_Food;
        final CreativeTabs Fallout_meds = InitCreativeTabs.Fallout_meds;
        final CreativeTabs Fallout_stats_blocks = InitCreativeTabs.Fallout_stats_blocks;
        final CreativeTabs Fallout_stats_armor = InitCreativeTabs.Fallout_armor;
        //final CreativeTabs Fallout_Util = InitCreativeTabs.Fallout_Utils;
        SoundInit.registerSounds();

        OBJLoader.INSTANCE.addDomain(GlobalNames.Domain);

        for (int i = 0; i < (GlobalBlockArray.blocks.length - 1); i++) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.blocks[i]), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.blocksNames[i], "inventory"));
        }

        //ITEMS SECTION#########################################
        for (int it = 0; it < (GlobalItemArray.items.length - 1); it++) {
            final int DEFAULT_ITEM_SUBTYPE = 0;
            ModelLoader.setCustomModelResourceLocation(GlobalItemArray.items[it], DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalItemArray.ItemNames[it], "inventory"));
        }

    }

    public static void initClientOnly() {
        for (int i = 0; i < (GlobalBlockArray.blocks.length - 1); i++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GlobalBlockArray.blocks[i]), 0, new ModelResourceLocation(MODID + ":" + GlobalBlockArray.blocksNames[i]));

        }
        //RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RenderBullet::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet(Minecraft.getMinecraft().getRenderManager(), new ModelBullet(), 0.5));
    }


    public static void postInitClientOnly() {
  /* Here, we register the event handler that modifies the overlay. Since
   * the overlay is a GUI element, and the GUI only exists on the client side,
   * we only register this event handler on the client side.
   */
        MinecraftForge.EVENT_BUS.register(new EventHandlerOverlayPipBuck());
        MinecraftForge.EVENT_BUS.register(new EventHandlerOverlayAEM());

        MinecraftForge.EVENT_BUS.register(new EventPlayerGuiHandler());
        MinecraftForge.EVENT_BUS.register(new EventPlayerRenders());
    }
}
