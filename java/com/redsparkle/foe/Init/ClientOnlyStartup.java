package com.redsparkle.foe.Init;

import com.redsparkle.foe.block.containers.SparkleColaMachineBlock;
import com.redsparkle.foe.block.effectDispenser.RadiationBlock;
import com.redsparkle.foe.block.interractable.DesktopTerminal;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.events.EventHandlerOverlayPipBuck;
import com.redsparkle.foe.events.EventPlayerRenders;
import com.redsparkle.foe.gui.Overlays.PipBuckOverlay;
import com.redsparkle.foe.items.guns.inits.bulletFiredGuns.EntityBullet;
import com.redsparkle.foe.items.guns.inits.bulletFiredGuns.render.RenderBulletEntity;
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
        final CreativeTabs Fallout_meds = InitCreativeTabs.Fallout_meds;
        final CreativeTabs Fallout_stats_blocks = InitCreativeTabs.Fallout_stats_blocks;
        final CreativeTabs Fallout_stats_armor = InitCreativeTabs.Fallout_armor;
        //final CreativeTabs Fallout_Util = InitCreativeTabs.Fallout_Utils;
        SoundInit.registerSounds();
        OBJLoader.INSTANCE.addDomain(GlobalNames.Domain);
        Item SPCmachine = Item.getItemFromBlock(SparkleColaMachineBlock.instance);
        ModelLoader.setCustomModelResourceLocation(SPCmachine, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.SPCmachine, "inventory"));

        Item Dterminal = Item.getItemFromBlock(DesktopTerminal.instance);
        ModelLoader.setCustomModelResourceLocation(Dterminal, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Terminal, "inventory"));


        //ITEMS SECTION#########################################

        //---------------------UTILITY--------------------------
        final int DEFAULT_ITEM_SUBTYPE = 0;
        ModelLoader.setCustomModelResourceLocation(ItemInit.pipbuck, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.PipbuckTT, "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemInit.lvlingCrystall, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.LevelingCrystall, "inventory"));

        //---------------------MEDS--------------------------
        ModelLoader.setCustomModelResourceLocation(ItemInit.radAway, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.RadAway, "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemInit.radx, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.RadX, "inventory"));

        //---------------------AMMO--------------------------
        ModelLoader.setCustomModelResourceLocation(ItemInit.tenMMAmmo, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.TenMMAmmo, "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemInit.tenMMClip, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.TenMMClip, "inventory"));

        ModelLoader.setCustomModelResourceLocation(ItemInit.battery, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Battery, "inventory"));

        //---------------------GUNS--------------------------
        ModelLoader.setCustomModelResourceLocation(ItemInit.tenMM, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.TenMM, "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemInit.laserPistol, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.LaserPistol, "inventory"));


        //---------------------ARMOR--------------------------
        ModelLoader.setCustomModelResourceLocation(ItemInit.t60head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T60Head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemInit.t60body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T60Body, "inventory"));

        ModelLoader.setCustomModelResourceLocation(ItemInit.t50head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T50Head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemInit.t50body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T50Body, "inventory"));

        ModelLoader.setCustomModelResourceLocation(ItemInit.t40head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T40Head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemInit.t40body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T40Body, "inventory"));
    }

    public static void initClientOnly() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(SparkleColaMachineBlock.instance), 0, new ModelResourceLocation(MODID + ":" + GlobalNames.SPCmachine));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(RadiationBlock.instance), 0, new ModelResourceLocation(MODID + ":" + GlobalNames.RadBlock));


        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RenderBulletEntity::new);
    }


    public static void postInitClientOnly() {
  /* Here, we register the event handler that modifies the overlay. Since
   * the overlay is a GUI element, and the GUI only exists on the client side,
   * we only register this event handler on the client side.
   */
        pipBuckGui = new PipBuckOverlay(Minecraft.getMinecraft());
        MinecraftForge.EVENT_BUS.register(new EventHandlerOverlayPipBuck(pipBuckGui));
        MinecraftForge.EVENT_BUS.register(new EventPlayerRenders());
    }
}
