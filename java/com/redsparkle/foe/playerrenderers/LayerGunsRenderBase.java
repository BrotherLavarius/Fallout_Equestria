package com.redsparkle.foe.playerrenderers;


import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by NENYN on 1/23/2017.
 */
@SideOnly(Side.CLIENT)
public  class LayerGunsRenderBase extends RenderPlayer {
    public LayerGunsRenderBase(RenderManager renderManager) {
        super(renderManager);
    }

    public LayerGunsRenderBase(RenderManager renderManager, boolean useSmallArms) {
        super(renderManager, useSmallArms);
        //this.addLayer(new GunRender(this));
    }


}

