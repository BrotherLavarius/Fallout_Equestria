package com.redsparkle.foe.playerrenderers;


import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by NENYN on 1/23/2017.
 */
@SideOnly(Side.CLIENT)
public class FoeGunsRender implements LayerRenderer<EntityPlayer> {


    public FoeGunsRender(RenderPlayer renderer) {

    }


    @Override
    public void doRenderLayer(EntityPlayer player, float v, float v1, float v2, float v3, float v4, float v5, float v6) {

    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
