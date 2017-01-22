package com.redsparkle.foe.playerrenderers;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by NENYN on 1/23/2017.
 */
public class FoeGunsRender implements LayerRenderer<EntityPlayer>{
    @Override
    public void doRenderLayer(EntityPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    //TODO: make this render layer wor, and work for instanceof Gun items
}
