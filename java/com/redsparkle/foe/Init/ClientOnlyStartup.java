package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalBlockArray;
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
import com.redsparkle.foe.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.redsparkle.api.utils.GlobalItemArray_For_init.*;

/**
 * Created by hoijima on 23.09.16.
 */
@Mod.EventBusSubscriber(modid = main.MODID)
public class ClientOnlyStartup {
    private static PipBuckOverlay pipBuckGui;


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        OBJLoader.INSTANCE.addDomain(GlobalNames.Domain);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.SparkleColaMachineBlock), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.SparkleColaMachineBlock, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.RadiationBlock), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.RadiationBlock, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.armor_bench_tier_one), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.armor_bench_tier_one, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.DesktopTerminal), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.DesktopTerminal, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.locker), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.locker, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.workbench), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.workbench, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.workbench_handmade), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.workbench_handmade, "inventory"));

        ModelLoader.setCustomModelResourceLocation(radx, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + radx, "inventory"));
        ModelLoader.setCustomModelResourceLocation(radAway, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + radAway, "inventory"));
        ModelLoader.setCustomModelResourceLocation(redHealPotion, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + redHealPotion, "inventory"));
        ModelLoader.setCustomModelResourceLocation(whiteHealpotion, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + whiteHealpotion, "inventory"));
        ModelLoader.setCustomModelResourceLocation(purpleHealPotion, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + purpleHealPotion, "inventory"));
        ModelLoader.setCustomModelResourceLocation(darkHealPotion, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + darkHealPotion, "inventory"));
        ModelLoader.setCustomModelResourceLocation(pipbuck, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + pipbuck, "inventory"));
        ModelLoader.setCustomModelResourceLocation(lvlingCrystall, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + lvlingCrystall, "inventory"));
        ModelLoader.setCustomModelResourceLocation(aem, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + aem, "inventory"));
        ModelLoader.setCustomModelResourceLocation(potato_crisps, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + potato_crisps, "inventory"));
        ModelLoader.setCustomModelResourceLocation(blamko_macCheese, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + blamko_macCheese, "inventory"));
        ModelLoader.setCustomModelResourceLocation(dandy_boy_apples, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + dandy_boy_apples, "inventory"));
        ModelLoader.setCustomModelResourceLocation(gumDrops, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + gumDrops, "inventory"));
        ModelLoader.setCustomModelResourceLocation(sugar_bombs, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + sugar_bombs, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fancy_mare_snack_cakes, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + fancy_mare_snack_cakes, "inventory"));
        ModelLoader.setCustomModelResourceLocation(tenMMAmmo, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + tenMMAmmo, "inventory"));
        ModelLoader.setCustomModelResourceLocation(tenMMClip, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + tenMMClip, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fourTenMMammo, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + fourTenMMammo, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fourTenMMClip, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + fourTenMMClip, "inventory"));
        ModelLoader.setCustomModelResourceLocation(battery, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + battery, "inventory"));
        ModelLoader.setCustomModelResourceLocation(sShell, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + sShell, "inventory"));
        ModelLoader.setCustomModelResourceLocation(flare, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + flare, "inventory"));
        ModelLoader.setCustomModelResourceLocation(tenMMbulletCase, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + tenMMbulletCase, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fourTenMMbulletCase, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + fourTenMMbulletCase, "inventory"));
        ModelLoader.setCustomModelResourceLocation(tenMM, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + tenMM, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fourTenMM, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + fourTenMM, "inventory"));
        ModelLoader.setCustomModelResourceLocation(laserPistol, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + laserPistol, "inventory"));
        ModelLoader.setCustomModelResourceLocation(sb_shoutgun, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + sb_shoutgun, "inventory"));
        ModelLoader.setCustomModelResourceLocation(flareGun, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + flareGun, "inventory"));
        ModelLoader.setCustomModelResourceLocation(itb, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + itb, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t40head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t40head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t40body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t40body, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t40legs, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t40legs, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t50head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t50head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t50body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t50body, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t50legs, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t50legs, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t60head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t60head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t60body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t60body, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t60legs, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + t60legs, "inventory"));

    }





    public static void preInitClientOnly() {

        final CreativeTabs Fallout_ammo = InitCreativeTabs.Fallout_ammo;
        final CreativeTabs Fallout_blocks = InitCreativeTabs.Fallout_blocks;
        final CreativeTabs Fallout_guns = InitCreativeTabs.Fallout_guns;
        final CreativeTabs Fallout_food = InitCreativeTabs.Fallout_Food;
        final CreativeTabs Fallout_meds = InitCreativeTabs.Fallout_meds;
        final CreativeTabs Fallout_stats_blocks = InitCreativeTabs.Fallout_stats_blocks;
        final CreativeTabs Fallout_stats_armor = InitCreativeTabs.Fallout_armor;
        //final CreativeTabs Fallout_Util = InitCreativeTabs.Fallout_Utils;
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderFactoryLaser(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityFlame.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_one.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_two.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_tree.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_four.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_five.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(Pellet_six.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityFlare.class, new RenderFactoryBullet(Minecraft.getMinecraft().getRenderManager()));

//        for (int i = 0; i < (GlobalBlockArray.blocks.length - 1); i++) {
//            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.blocks[i]), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.blocksNames[i], "inventory"));
//
//            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlobalBlockArray.RadiationBlock,0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalBlockArray.RadiationBlock, "inventory"));
//
//        }
//        //ITEMS SECTION#########################################
//        for (int it = 0; it <= (GlobalItemArray_For_init.AllInit.length - 1); it++) {
//            final int DEFAULT_ITEM_SUBTYPE = 0;
//            if (GlobalItemArray_For_init.AllInit[it] != null) {
//                ModelLoader.setCustomModelResourceLocation(GlobalItemArray_For_init.AllInit[it], DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalItemArray_For_init.ItemNames[it], "inventory"));
//            }
//        }
//        for (int ar = 0; ar <= (GlobalItemModelsInitArray.AllInit.length - 1); ar++) {
//            final int DEFAULT_ITEM_SUBTYPE = 0;
//            if (GlobalItemModelsInitArray.AllInit[ar] != null) {
//                ModelLoader.setCustomModelResourceLocation(GlobalItemModelsInitArray.AllInit[ar], DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalItemModelsInitArray.ItemNames[ar], "inventory"));
//            }
//        }
//    }
//    public static void initClientOnly() {
//        for (int i = 0; i < (GlobalBlockArray.blocks.length - 1); i++) {
//            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GlobalBlockArray.blocks[i]), 0, new ModelResourceLocation(MODID + ":" + GlobalBlockArray.blocksNames[i]));
//        }
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
