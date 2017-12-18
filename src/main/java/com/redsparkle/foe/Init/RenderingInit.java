package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.Init.model.MeshDefinitionFix;
import com.redsparkle.foe.items.guns.entitys.bass.EntityBass;
import com.redsparkle.foe.items.guns.entitys.bass.RenderFactoryBass;
import com.redsparkle.foe.items.guns.entitys.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.entitys.bulletFired.RenderFactoryBullet;
import com.redsparkle.foe.items.guns.entitys.flametrower.EntityFlame;
import com.redsparkle.foe.items.guns.entitys.flare.EntityFlare;
import com.redsparkle.foe.items.guns.entitys.laserFired.EntityLaser;
import com.redsparkle.foe.items.guns.entitys.laserFired.RenderFactoryLaser;
import com.redsparkle.foe.items.guns.entitys.spreadPellet_shotgun.*;
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

import java.util.Map;

import static com.redsparkle.foe.Init.ItemInit.*;
import static com.redsparkle.foe.main.MODID;


/**
 * Created by hoijima on 23.09.16.
 */

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MODID)
public class RenderingInit {
    public static final RenderingInit INSTANCE = new RenderingInit();

    private static final String FLUID_MODEL_PATH = GlobalNames.Domain + ":" + "fluid";

    private RenderingInit() {
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        OBJLoader.INSTANCE.addDomain(GlobalNames.Domain);

        for(Map.Entry<Block,String> entry : BlockInit.BLOCKS.entrySet()) {
            Block block = entry.getKey();
            String name = entry.getValue();
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),0, new ModelResourceLocation(GlobalNames.Domain + ":" +name, "normal"));
        }


        for(Map.Entry<Item,String> entry : ITEMS.entrySet()) {
                Item item = entry.getKey();
                String name = entry.getValue();
                ModelLoader.setCustomModelResourceLocation(item,0, new ModelResourceLocation(GlobalNames.Domain + ":" +name, "inventory"));
        }



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
        RenderingRegistry.registerEntityRenderingHandler(EntityBass.class, new RenderFactoryBass(Minecraft.getMinecraft().getRenderManager()));


        INSTANCE.registerFluidModels();
    }

    private static void registerTexture(Item item, String textureName) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(MODID + ":" + textureName, "inventory"));
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
