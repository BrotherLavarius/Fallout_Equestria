package com.redsparkle.foe.playerrenderers;


import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.items.guns.TenMM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
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

