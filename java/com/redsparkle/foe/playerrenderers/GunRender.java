package com.redsparkle.foe.playerrenderers;

import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

import static java.lang.Math.abs;
/**
 * Created by NENYN on 2/12/2017.
 */
public class GunRender implements LayerRenderer<EntityLivingBase> {
    private final RenderPlayer playerRenderer;
    public GunRender(RenderPlayer playerRendererIn) {
        this.playerRenderer = playerRendererIn;
    }
    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);
        Item item = itemstack.getItem();
        Minecraft minecraft = Minecraft.getMinecraft();
        Float yawCorrector = 0F;
        Float rotationPitch = 0F;
        if (itemstack != null && item instanceof Item_Firearm) {
            GlStateManager.pushMatrix();
            if (MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) >= 0.0F) {
                yawCorrector = MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y);
            } else if (MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) < 0.0F) {
                yawCorrector = abs(MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) + 360);
            }
            if (MathHelper.wrapDegrees(entitylivingbaseIn.rotationPitch) >= 0.0F) {
                rotationPitch = MathHelper.wrapDegrees(entitylivingbaseIn.rotationPitch);
            } else if (MathHelper.wrapDegrees(entitylivingbaseIn.rotationPitch) < 0.0F) {
                rotationPitch = abs(MathHelper.wrapDegrees(entitylivingbaseIn.rotationPitch) + 360);
            }
            GlStateManager.translate(0.0F, 0.0F, 0.0F);
            GlStateManager.rotate(netHeadYaw, 0, 1.0F, 0);
            GlStateManager.rotate(headPitch, 1.0F, 0, 0);
            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
    }
    public boolean shouldCombineTextures() {
        return false;
    }
}
