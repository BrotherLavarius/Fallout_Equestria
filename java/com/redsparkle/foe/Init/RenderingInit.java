package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.Init.model.MeshDefinitionFix;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.bulletFired.RenderFactoryBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.laserFired.RenderFactoryLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
import com.redsparkle.foe.main;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.redsparkle.foe.Init.ItemInit.*;


/**
 * Created by hoijima on 23.09.16.
 */
@Mod.EventBusSubscriber(modid = main.MODID)
public class RenderingInit {
    public static final RenderingInit INSTANCE = new RenderingInit();

    private static final String FLUID_MODEL_PATH = GlobalNames.Domain + ":" + "fluid";


    private RenderingInit() {
    }



    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        OBJLoader.INSTANCE.addDomain(GlobalNames.Domain);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockInit.SparkleColaMachineBlock), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.SPCmachine, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockInit.RadiationBlock), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.RadBlock, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockInit.armor_bench_tier_one), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.ArmorBench_tier_one, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockInit.DesktopTerminal), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Terminal, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockInit.locker), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Locker, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockInit.workbench), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Workbench, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockInit.workbench_handmade), 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Workbench_handmade, "inventory"));

        ModelLoader.setCustomModelResourceLocation(radx, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.RadX, "inventory"));
        ModelLoader.setCustomModelResourceLocation(radAway, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.RadAway, "inventory"));
        ModelLoader.setCustomModelResourceLocation(redHealPotion, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.redHealPotion, "inventory"));
        ModelLoader.setCustomModelResourceLocation(whiteHealpotion, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.whiteHealpotion, "inventory"));
        ModelLoader.setCustomModelResourceLocation(purpleHealPotion, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.purpleHealPotion, "inventory"));
        ModelLoader.setCustomModelResourceLocation(darkHealPotion, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.darkHealPotion, "inventory"));
        ModelLoader.setCustomModelResourceLocation(pipbuck, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.PipbuckTT, "inventory"));
        ModelLoader.setCustomModelResourceLocation(lvlingCrystall, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.LevelingCrystall, "inventory"));
        ModelLoader.setCustomModelResourceLocation(aem, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.AEM, "inventory"));
        ModelLoader.setCustomModelResourceLocation(potato_crisps, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.potato_crisps, "inventory"));
        ModelLoader.setCustomModelResourceLocation(blamko_macCheese, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.blamko_macCheese, "inventory"));
        ModelLoader.setCustomModelResourceLocation(dandy_boy_apples, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.dandy_boy_apples, "inventory"));
        ModelLoader.setCustomModelResourceLocation(gumDrops, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.gumDrops, "inventory"));
        ModelLoader.setCustomModelResourceLocation(sugar_bombs, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.sugar_bombs, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fancy_mare_snack_cakes, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Fancy_mare_Snack_Cakes, "inventory"));
        ModelLoader.setCustomModelResourceLocation(tenMMAmmo, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.TenMMAmmo, "inventory"));
        ModelLoader.setCustomModelResourceLocation(tenMMClip, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.TenMMClip, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fourTenMMammo, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.FourTenMMAmmo, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fourTenMMClip, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.FourTenMMClip, "inventory"));
        ModelLoader.setCustomModelResourceLocation(battery, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.Battery, "inventory"));
        ModelLoader.setCustomModelResourceLocation(sShell, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.shell, "inventory"));
        ModelLoader.setCustomModelResourceLocation(flare, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.flare, "inventory"));
        ModelLoader.setCustomModelResourceLocation(tenMMbulletCase, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.TenMMCase, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fourTenMMbulletCase, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.FourTenMMCase, "inventory"));
        ModelLoader.setCustomModelResourceLocation(tenMM, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.TenMM, "inventory"));
        ModelLoader.setCustomModelResourceLocation(fourTenMM, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.FourTenMM, "inventory"));
        ModelLoader.setCustomModelResourceLocation(laserPistol, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.LaserPistol, "inventory"));
        ModelLoader.setCustomModelResourceLocation(sb_shoutgun, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.db_shoutgun, "inventory"));
        ModelLoader.setCustomModelResourceLocation(flareGun, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.flare_gun, "inventory"));
        ModelLoader.setCustomModelResourceLocation(itb, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + "bullet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(t40head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T40Head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t40body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T40Body, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t40legs, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T40Legs, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t50head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T50Head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t50body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T50Body, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t50legs, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T50Legs, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t60head, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T60Head, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t60body, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T60Body, "inventory"));
        ModelLoader.setCustomModelResourceLocation(t60legs, 0, new ModelResourceLocation(GlobalNames.Domain + ":" + GlobalNames.T60Legs, "inventory"));

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

        INSTANCE.registerFluidModels();
    }

    private void registerFluidModels() {
        FluidsInit.MOD_FLUID_BLOCKS.forEach(this::registerFluidModel);
    }

    private void registerFluidModel(final IFluidBlock fluidBlock) {
        final Item item = Item.getItemFromBlock((Block) fluidBlock);
        assert item != Items.AIR;

        ModelBakery.registerItemVariants(item);

        final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FLUID_MODEL_PATH, fluidBlock.getFluid().getName());

        ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelResourceLocation));

        ModelLoader.setCustomStateMapper((Block) fluidBlock, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(final IBlockState p_178132_1_) {
                return modelResourceLocation;
            }
        });
    }
}
