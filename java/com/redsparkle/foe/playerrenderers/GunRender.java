package com.redsparkle.foe.playerrenderers;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.items.saddlebags.Saddlebags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
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
        ITrigger_item triggeritem = minecraft.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null);

        ItemStack harnes = entitylivingbaseIn.getCapability(IAdvProvider.Adv_Inv,null).getStackInSlot(5);
        Item itemHarness = harnes.getItem();

        ItemStack RS_GUN = entitylivingbaseIn.getCapability(IAdvProvider.Adv_Inv,null).getStackInSlot(7);
        ItemStack LS_GUN = entitylivingbaseIn.getCapability(IAdvProvider.Adv_Inv,null).getStackInSlot(6);

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
            if (headPitch >= 33.0) {
                GlStateManager.rotate(33, 1.0F, 0, 0);
            } else if (headPitch < -67.0) {
                GlStateManager.rotate(-67, 1.0F, 0, 0);
            } else {
                GlStateManager.rotate(headPitch, 1.0F, 0, 0);
            }



            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
        if (triggeritem.getStatus() && itemstack.getItem() == Items.AIR) {
            Item trigger_item = ItemInit.trigger_item;
            ItemStack trigger_item_stack = new ItemStack(trigger_item);
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
            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, trigger_item_stack, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
        if (itemHarness != null && itemHarness instanceof Saddlebags && RS_GUN.getItem() instanceof Item_SaggleBagGun) {
            if(((Item_SaggleBagGun)RS_GUN.getItem()).side()  == "RS") {
                GlStateManager.pushMatrix();
                GlStateManager.scale(1.5,1.5,1.5);

                //GlStateManager.rotate(180,0,1,0);
                GlStateManager.translate(-0.25, -0.198, -0.045);
                if (entitylivingbaseIn.isSneaking()) {
                    GlStateManager.translate(0.280F, 0.79F, 0F);
                } else {
                    GlStateManager.translate(0.280F, 0.65F, 0F);
                }
                minecraft.getItemRenderer().renderItem(entitylivingbaseIn, RS_GUN, ItemCameraTransforms.TransformType.HEAD);
                GlStateManager.popMatrix();
            }
        }
        if (itemHarness != null && itemHarness instanceof Saddlebags && LS_GUN.getItem() instanceof Item_SaggleBagGun) {
            if(((Item_SaggleBagGun)LS_GUN.getItem()).side() == "LS") {
//                GlStateManager.pushMatrix();
//                GlStateManager.scale(1.5,1.5,1.5);
//                GlStateManager.rotate(180,0,1,0);
//                GlStateManager.translate(-0.80,0,0.65);
//                if (entitylivingbaseIn.isSneaking()) {
//                    GlStateManager.translate(0.280F, 0.85F, 0F);
//                } else {
//                    GlStateManager.translate(0.280F, 0.65F, 0F);
//                }
//                minecraft.getItemRenderer().renderItem(entitylivingbaseIn, LS_GUN, ItemCameraTransforms.TransformType.HEAD);
//                GlStateManager.popMatrix();
            }
        }


    }
    public boolean shouldCombineTextures() {
        return false;
    }
}
