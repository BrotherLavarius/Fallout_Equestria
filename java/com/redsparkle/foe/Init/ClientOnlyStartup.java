package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalBlockArray;
import com.redsparkle.api.utils.GlobalItemArray_For_init;
import com.redsparkle.api.utils.GlobalItemModelsInitArray;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.events.character.EventPlayerRenders;
import com.redsparkle.foe.events.gui.EventHandlerOverlayAEM;
import com.redsparkle.foe.events.gui.EventHandlerOverlayPipBuck;
import com.redsparkle.foe.events.gui.EventPlayerGuiHandler;
import com.redsparkle.foe.gui.Overlays.PipBuckOverlay;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.bulletFired.RenderFactoryBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.laserFired.RenderFactoryLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
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
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderFactoryLaser(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityFlame.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));

        RenderingRegistry.registerEntityRenderingHandler(Pellet.class,  new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_one.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_two.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_tree.class,new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_four.class,new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_five.class,new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_six.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));

        RenderingRegistry.registerEntityRenderingHandler(EntityFlare.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));

        OBJLoader.INSTANCE.addDomain(GlobalNames.Domain);

        for (int i = 0; i < (GlobalBlockArray.blocks.length - 1); i++) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.blocks[i]), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.blocksNames[i], "inventory"));
        }

        //ITEMS SECTION#########################################
        for (int it = 0; it <= (GlobalItemArray_For_init.AllInit.length - 1); it++) {
            final int DEFAULT_ITEM_SUBTYPE = 0;
            if (GlobalItemArray_For_init.AllInit[it] != null) {
                ModelLoader.setCustomModelResourceLocation(GlobalItemArray_For_init.AllInit[it], DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalItemArray_For_init.ItemNames[it], "inventory"));
            }
        }


        for (int ar = 0; ar <= (GlobalItemModelsInitArray.AllInit.length - 1); ar++) {
            final int DEFAULT_ITEM_SUBTYPE = 0;
            if (GlobalItemModelsInitArray.AllInit[ar] != null) {
                ModelLoader.setCustomModelResourceLocation(GlobalItemModelsInitArray.AllInit[ar], DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalItemModelsInitArray.ItemNames[ar], "inventory"));
            }
        }

    }

    public static void initClientOnly() {
        for (int i = 0; i < (GlobalBlockArray.blocks.length - 1); i++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GlobalBlockArray.blocks[i]), 0, new ModelResourceLocation(MODID + ":" + GlobalBlockArray.blocksNames[i]));

        }



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
