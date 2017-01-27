package com.redsparkle.foe.playerrenderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by NENYN on 1/26/2017.
 */
@SideOnly(Side.CLIENT)
public class RenderGenericLiving extends RenderPlayer {


    public RenderGenericLiving(RenderManager p_i46102_1_) {
        super(p_i46102_1_);
    }
}
