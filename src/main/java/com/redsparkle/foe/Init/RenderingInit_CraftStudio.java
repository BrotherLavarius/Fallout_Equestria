package com.redsparkle.foe.Init;


import com.leviathanstudio.craftstudio.client.registry.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;
import com.leviathanstudio.craftstudio.client.util.EnumRenderType;
import com.leviathanstudio.craftstudio.client.util.EnumResourceType;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSTileEntitySpecialRenderer;
import com.redsparkle.foe.main;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class RenderingInit_CraftStudio {

    @CraftStudioLoader
    public static void register() {
        CSRegistryHelper registry = new CSRegistryHelper(main.MODID);
        registry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "alarm_lamp");
        registry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "generator_small");
//  registry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "dragon_brun");


        registry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "alarm_lamp_active");
        registry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "generator_small_active");
//        registry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "close_fan");
    }

    public static void bindTESR() {

        for (ModBlocks_CStudio block : ModBlocks_CStudio.values()) {
            ClientRegistry.bindTileEntitySpecialRenderer(block.getTE_CLASS(), new CSTileEntitySpecialRenderer(main.MODID, block.getNAME(), 64,
                    64, new ResourceLocation(main.MODID, "textures/blocks/" + block.getNAME() + ".png")));
        }
    }
}
